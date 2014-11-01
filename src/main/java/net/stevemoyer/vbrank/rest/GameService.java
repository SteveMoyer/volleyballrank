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

@Path("/games") @Produces(MediaType.APPLICATION_JSON)
public class GameService {
    PersistenceManager pm = PMF.getInstance().getPersistenceManager();

    @POST public Game insertGame(Game game) {
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
