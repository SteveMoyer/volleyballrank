package net.stevemoyer.vbrank.rest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;



@Path("/games") @Produces(MediaType.APPLICATION_JSON)
public class GameService {
  PersistenceManager pm = PMF.getManager();

  PlayerService playerService = new PlayerService();

  @POST public Game insertGame(Game game) {
    /// TODO: add transactions
    game.setGameDate(Calendar.getInstance().getTime());
    // TODO: get user email from somewhere legit
    String emailAddress = "steve@test.com";

    game.setPostedBy(emailAddress);
    //TODO: figure out how to attach the posted games rather than looking them up
    boolean teamABWon = game.getTeamABScore() > game.getTeamCDScore();
    game.setPlayerA(addPlayerGame(game.getPlayerA(), teamABWon));
    game.setPlayerB(addPlayerGame(game.getPlayerB(), teamABWon));
    game.setPlayerC(addPlayerGame(game.getPlayerC(), !teamABWon));
    game.setPlayerD(addPlayerGame(game.getPlayerD(), !teamABWon));
    Game updatedGame = pm.makePersistent(game);

    return updatedGame;
  }
  @GET @Path("/latest/{playerId}") public List<Game> getLatest(@PathParam("playerId") int playerId) {
    Query q = pm.newQuery("javax.jdo.query.SQL","select * from game where playerA_id = ?1 or playerB_id = ?1 or playerC_id = ?1 or playerD_id = ?1");
    q.setClass(Game.class);
    return (List<Game>) q.execute(playerId);
  }

  private Player addPlayerGame(Player player, boolean wonGame) {
    Player updatedPlayer = playerService.getPlayer(player.getId());
    if(wonGame) {
      updatedPlayer.setWins(player.getWins() + 1);
    } else {
      updatedPlayer.setLosses(player.getLosses() + 1);
    }
    return pm.makePersistent(updatedPlayer);
  }
}
