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

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;



@Path("/games") @Produces(MediaType.APPLICATION_JSON)
@Singleton
public class GameService {
  final Provider<PersistenceManager> pmf;
  final PlayerService playerService;
  //private static final Logger log = Logger.getLogger(PlayerService.class.getName());
@Inject
public GameService(Provider<PersistenceManager> pmf,PlayerService playerService) {
  this.pmf=pmf;
  this.playerService=playerService;
}

  @POST public Game insertGame(Game game) {
    /// TODO: add transactions
    game.setGameDate(Calendar.getInstance().getTime());
    // TODO: get user email from somewhere legit
    String emailAddress = "steve@test.com";

    game.setPostedBy(emailAddress);
    //TODO: figure out how to attach the posted games rather than looking them up
    boolean teamABWon = game.getTeamABScore() > game.getTeamCDScore();
    game.setPlayerA(playerService.addPlayerGame(game.getPlayerA(), teamABWon));
    game.setPlayerB(playerService.addPlayerGame(game.getPlayerB(), teamABWon));
    game.setPlayerC(playerService.addPlayerGame(game.getPlayerC(), !teamABWon));
    game.setPlayerD(playerService.addPlayerGame(game.getPlayerD(), !teamABWon));
    Game updatedGame = pmf.get().makePersistent(game);

    return updatedGame;
  }
  @GET @Path("/latest/{playerId}") public List<Game> getLatest(@PathParam("playerId") int playerId) {
    Query q = pmf.get().newQuery("javax.jdo.query.SQL","select * from game where playerA_id = ?1 or playerB_id = ?1 or playerC_id = ?1 or playerD_id = ?1");
    q.setClass(Game.class);
    return (List<Game>) q.execute(playerId);
  }
}
