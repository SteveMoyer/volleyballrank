package net.stevemoyer.vbrank.rest;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.users.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.PreparedQuery;
import javax.inject.Named;
import javax.jdo.Query;
import java.util.List;
import java.util.ArrayList;
import javax.jdo.PersistenceManager;

@Path("/players") @Produces(MediaType.APPLICATION_JSON)
public class PlayerService {
    PersistenceManager pm = PMF.getInstance().getPersistenceManager();

    @GET @Path("me") public Player getMe() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if(user ==null) {
            user = new User("anon@test.com","myauth.com");
        }
        String emailAddress = user.getEmail();
        Query q = pm.newQuery(Player.class);
        q.setFilter("emailAddress == emailAddressParam");
        q.declareParameters("String emailAddressParam");

        List<Player> players =(List<Player>) q.execute(emailAddress);

        if(!players.isEmpty()) {
            return players.get(0);
        }

        Player newMe = new Player();
        newMe.setEmailAddress(user.getEmail());
        newMe.setName(user.getNickname());
        return insertPlayer(newMe);
    }

    @GET @Path("{playerId}") public Player getPlayer(@PathParam("playerId") Long playerId) {
        return pm.getObjectById(Player.class,playerId);
    }

    @GET public List<Player> getStanding(){
        Query q = pm.newQuery(Player.class);
        q.setOrdering("winningPercentage desc, wins desc, losses");
        return (List<Player>) q.execute();
    }

    @POST public Player insertPlayer(Player player) {
        Player updatedPlayer = pm.makePersistent(player);
        PlayerRef newPlayerRef = new PlayerRef(updatedPlayer.getId(),updatedPlayer.getName());
        pm.makePersistent(newPlayerRef);
        return updatedPlayer;
    }
}
