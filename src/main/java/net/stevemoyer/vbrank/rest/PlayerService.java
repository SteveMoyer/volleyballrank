package net.stevemoyer.vbrank.rest;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
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

    @Path("/players")
@Produces(MediaType.APPLICATION_JSON) 
    public class PlayerService {
        PersistenceManager pm = PMF.getInstance().getPersistenceManager();

        @GET @Path("me")
            public Player getMe() {
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
        @GET @Path("{playerId}")
            public Player getPlayer(@PathParam("playerId") Long playerId) {
              return pm.getObjectById(Player.class,playerId); 
            }

        @GET public List<Player> getStanding(){
            ArrayList<Player> players =new ArrayList();
            players.add(new Player(1L, "anon1@test.com", "Anon", 5, 1));
            players.add(new Player(2L, "anon2@test.com", "Anon2", 4, 2));
            players.add( new Player(3L, "anon3@test.com", "Anon3", 2, 4));
            players.add(new Player(4L, "anon4@test.com", "Anon4", 1, 5));
            return players;

        }
        public Player insertPlayer(Player player) {
            Player updatedPlayer = pm.makePersistent(player);
            PlayerRef newPlayerRef = new PlayerRef(updatedPlayer.getId(),updatedPlayer.getName());
            pm.makePersistent(newPlayerRef);
            return updatedPlayer;
        }
    }
