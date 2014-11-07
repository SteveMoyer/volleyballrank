package net.stevemoyer.vbrank.rest;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
        List<Player> players = (List<Player>) q.execute(emailAddress);

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
    @GET @Path("refs") public List<PlayerRef> getPlayerRefs() {
        Query q = pm.newQuery(PlayerRef.class);
        q.setOrdering("name");
        return (List<PlayerRef>) q.execute();
    }

    @POST public Player insertPlayer(Player player) {
        Player updatedPlayer = pm.makePersistent(player);
        PlayerRef newPlayerRef = new PlayerRef(updatedPlayer.getId(),updatedPlayer.getName());
        pm.makePersistent(newPlayerRef);
        return updatedPlayer;
    }
}
