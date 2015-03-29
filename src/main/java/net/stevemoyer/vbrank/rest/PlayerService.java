package net.stevemoyer.vbrank.rest;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.logging.Logger;

@Path("/players") @Produces(MediaType.APPLICATION_JSON) @Singleton
public class PlayerService {
  PMF pmf;
  private static final Logger log = Logger.getLogger(PlayerService.class.getName());
@Inject
public PlayerService(PMF pmf) {
  this.pmf =pmf;
}

  @GET @Path("me") public Player getMe() {
   // UserService userService = UserServiceFactory.getUserService();
   // User user = userService.getCurrentUser();

    String emailAddress ="steve@test.com";
    Query q = pmf.get().newQuery(Player.class);
    q.setFilter("emailAddress == emailAddressParam");
    q.declareParameters("String emailAddressParam");

    List<Player> players = (List<Player>) q.execute(emailAddress);

    if(!players.isEmpty()) {
      return players.get(0);
    }
    throw new WebApplicationException(
        Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build());
  }

  @GET @Path("{playerId}") public Player getPlayer(@PathParam("playerId") int playerId) {
    return pmf.get().getObjectById(Player.class,playerId);
  }
  @POST @Path("{playerId}") public Player updatePlayer(@PathParam("playerId") int playerId,
      Player updatedPlayer) {
    Player me = getMe();
    if(me.getId() !=playerId || me.getId() != updatedPlayer.getId()) {
      log.severe(String.format("Mismatched player ids %s,%s,%s",playerId, me.getId(), updatedPlayer.getId()));
      throw new WebApplicationException(
          Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build());
    }
    me.setName(updatedPlayer.getName());
    return pmf.get().makePersistent(me);
  }
  @GET public List<Player> getStanding(){
    Query q = pmf.get().newQuery("javax.jdo.query.SQL","select * from player order by case when wins = 0 then 0.0 else cast(wins as Decimal(8,3))/(wins+losses) end desc");
    q.setClass(Player.class);
    Object result = q.execute();
    System.out.println(result);
    return (List<Player>) result;
  }
  @GET @Path("refs") public List<Player> getPlayers() {
    Query q = pmf.get().newQuery(Player.class);
    q.setOrdering("name");
    return (List<Player>) q.execute();
  }

  @POST public Player insertPlayer(Player player) {
    Player updatedPlayer = pmf.get().makePersistent(player);
    return updatedPlayer;

  }
}
