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
    PersistenceManager pm = PMF.getInstance().getPersistenceManager();

    @POST public Game insertGame(Game game) {
        /// TODO: add transactions
        game.updatePlayerIds();
        game.setGameDate(Calendar.getInstance().getTime());
        Game updatedGame = pm.makePersistent(game);
        addPlayerGame(game.getPlayerA(),game.getTeamABScore() > game.getTeamCDScore());
        addPlayerGame(game.getPlayerB(),game.getTeamABScore() > game.getTeamCDScore());
        addPlayerGame(game.getPlayerC(),game.getTeamABScore() < game.getTeamCDScore());
        addPlayerGame(game.getPlayerD(),game.getTeamABScore() < game.getTeamCDScore());
        pm.makePersistent(game);
        return updatedGame;
    }
    @GET @Path("/latest/{playerId}") public List<Game> getLatest(@PathParam("playerId") Long playerId) {
        List<Game> games = new ArrayList();
        // TODO: WTF datastore is clearly the wrong tool for this job. Find something else.
        games.addAll(getGamesWithPlayerForSlot("playerAId", playerId));
        games.addAll(getGamesWithPlayerForSlot("playerBId", playerId));
        games.addAll(getGamesWithPlayerForSlot("playerCId", playerId));
        games.addAll(getGamesWithPlayerForSlot("playerDId", playerId));
        Collections.sort(games, new Comparator<Game>() {
            public int compare( Game a, Game b) {
                return a.getGameDate().compareTo(b.getGameDate());
            }

        });
        return games;
    }
    private List<Game> getGamesWithPlayerForSlot(String slot, Long playerId) {
        Query q = pm.newQuery(Game.class);
        q.setFilter(slot + " == playerId");
        q.declareParameters("Long playerId");
        return (List<Game>) q.execute(playerId);

    }
    private void addPlayerGame(PlayerRef playerRef, boolean wonGame) {
        Player player = pm.getObjectById(Player.class,playerRef.getId());
        if(wonGame) {
            player.setWins(player.getWins()+1);
        } else {
            player.setLosses(player.getLosses()+1);
        }
        pm.makePersistent(player);
    }
}
