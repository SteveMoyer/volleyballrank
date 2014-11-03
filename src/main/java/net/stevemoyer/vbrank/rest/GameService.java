package net.stevemoyer.vbrank.rest;
import javax.jdo.PersistenceManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/games") @Produces(MediaType.APPLICATION_JSON)
public class GameService {
    PersistenceManager pm = PMF.getInstance().getPersistenceManager();

    @POST public Game insertGame(Game game) {
        /// TODO: add transactions
        Game updatedGame = pm.makePersistent(game);
        addPlayerGame(game.getPlayerA(),game.getTeamABScore() > game.getTeamCDScore());
        addPlayerGame(game.getPlayerB(),game.getTeamABScore() > game.getTeamCDScore());
        addPlayerGame(game.getPlayerC(),game.getTeamABScore() < game.getTeamCDScore());
        addPlayerGame(game.getPlayerD(),game.getTeamABScore() < game.getTeamCDScore());
        pm.makePersistent(game);
        return updatedGame;
    }
    private void addPlayerGame(Player player, boolean wonGame) {
        if(wonGame) {
            player.setWins(player.getWins()+1);
        } else {
            player.setLosses(player.getLosses()+1);
        }
        pm.makePersistent(player);
    }
}
