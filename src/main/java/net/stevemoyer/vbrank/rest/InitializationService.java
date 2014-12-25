package net.stevemoyer.vbrank.rest;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Date;
import java.util.logging.Logger;

@Path("/initialize") @Produces(MediaType.APPLICATION_JSON)
public class InitializationService {
    private static final Logger log = Logger.getLogger(PlayerService.class.getName());
    private static final Player steve = new Player("steve@test.com","Steve");
    private static final Player eric = new Player("eric@test.com","Eric");
    private static final Player yung = new Player("yung@test.com","Yung");
    private static final Player eze = new Player("eze@test.com","Eze");
    private static final Player nick = new Player("nick@test.com","Nick");
    private static final Player uli = new Player("uli@test.com","Uli");
    private static final Player jens = new Player("jens@test.com","Jens");
    PersistenceManager pm = PMF.getInstance().getPersistenceManager();
    @GET public int getInitialize() {
        if(SystemProperty.environment.value() != SystemProperty.Environment.Value.Production) {

            UserService userService = UserServiceFactory.getUserService();
            if(userService.isUserAdmin()){
                deleteAllData();
                populatePlayers();
                populateGames();
            }
        }
        throw new WebApplicationException(
                Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_JSON).build());

    }
    private void populatePlayers() {
        PlayerService playerService = new PlayerService();
        playerService.insertPlayer(steve);
        playerService.insertPlayer(eric);
        playerService.insertPlayer(yung);
        playerService.insertPlayer(eze);
        playerService.insertPlayer(nick);
        playerService.insertPlayer(uli);
        playerService.insertPlayer(jens);
    }
    private void populateGames() {
        GameService gameService = new GameService();
        gameService.insertGame(new Game(steve.getRef(),eric.getRef(),yung.getRef(),eze.getRef(),21,19, new Date(2014,11,15)));
        gameService.insertGame(new Game(steve.getRef(),yung.getRef(),eze.getRef(),eric.getRef(),21,19, new Date(2014,11,15)));
        gameService.insertGame(new Game(steve.getRef(),eze.getRef(),eric.getRef(),yung.getRef(),21,19, new Date(2014,11,15)));
        gameService.insertGame(new Game(steve.getRef(),eric.getRef(),yung.getRef(),eze.getRef(),21,19, new Date(2014,11,15)));
        gameService.insertGame(new Game(steve.getRef(),yung.getRef(),eric.getRef(),eze.getRef(),21,19, new Date(2014,11,15)));

        gameService.insertGame(new Game(jens.getRef(),eric.getRef(),yung.getRef(),uli.getRef(),21,17, new Date(2014,11,17)));
        gameService.insertGame(new Game(jens.getRef(),yung.getRef(),uli.getRef(),eric.getRef(),21,19, new Date(2014,11,17)));
        gameService.insertGame(new Game(jens.getRef(),uli.getRef(),eric.getRef(),yung.getRef(),26,28, new Date(2014,11,17)));
        gameService.insertGame(new Game(jens.getRef(),eric.getRef(),yung.getRef(),uli.getRef(),21,15, new Date(2014,11,17)));
        gameService.insertGame(new Game(jens.getRef(),yung.getRef(),eric.getRef(),uli.getRef(),21,19, new Date(2014,11,17)));

        gameService.insertGame(new Game(jens.getRef(),eze.getRef(),steve.getRef(),uli.getRef(),21,19, new Date(2014,11,19)));
        gameService.insertGame(new Game(jens.getRef(),steve.getRef(),uli.getRef(),eze.getRef(),14,21, new Date(2014,11,19)));
        gameService.insertGame(new Game(jens.getRef(),uli.getRef(),eze.getRef(),steve.getRef(),21,19, new Date(2014,11,19)));
        gameService.insertGame(new Game(jens.getRef(),eze.getRef(),steve.getRef(),uli.getRef(),19,21, new Date(2014,11,19)));
        gameService.insertGame(new Game(jens.getRef(),steve.getRef(),eze.getRef(),uli.getRef(),21,19, new Date(2014,11,19)));

        gameService.insertGame(new Game(uli.getRef(),nick.getRef(),yung.getRef(),eze.getRef(),21,19, new Date(2014,11,23)));
        gameService.insertGame(new Game(uli.getRef(),yung.getRef(),eze.getRef(),nick.getRef(),21,19, new Date(2014,11,23)));
        gameService.insertGame(new Game(uli.getRef(),eze.getRef(),nick.getRef(),yung.getRef(),21,19, new Date(2014,11,23)));
        gameService.insertGame(new Game(uli.getRef(),nick.getRef(),yung.getRef(),eze.getRef(),18,21, new Date(2014,11,23)));
        gameService.insertGame(new Game(uli.getRef(),yung.getRef(),nick.getRef(),eze.getRef(),21,19, new Date(2014,11,23)));



 
    }
    private void deleteAllData() {
        deleteAllEntity(Game.class);
        deleteAllEntity(Player.class);
        deleteAllEntity(PlayerRef.class);
    }
    private void deleteAllEntity(Class<?> clazz) {
        Query q = pm.newQuery(clazz);
        q.deletePersistentAll();

    }



}
 
