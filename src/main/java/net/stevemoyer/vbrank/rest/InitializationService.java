package net.stevemoyer.vbrank.rest;

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

  @GET
  public int getInitialize() {

    // TODO: check for admin
    deleteAllData();
    populatePlayers();
    populateGames();
    return 1;
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
    gameService.insertGame(new Game(steve,eric,yung,eze,21,19, new Date(2014,11,15),"steve@test.com"));
     gameService.insertGame(new Game(steve,yung,eze,eric,21,19, new Date(2014,11,15),"steve@test.com"));
     gameService.insertGame(new Game(steve,eze,eric,yung,21,19, new Date(2014,11,15),"steve@test.com"));
     gameService.insertGame(new Game(steve,eric,yung,eze,21,19, new Date(2014,11,15),"steve@test.com"));
     gameService.insertGame(new Game(steve,yung,eric,eze,21,19, new Date(2014,11,15),"steve@test.com"));

     gameService.insertGame(new Game(jens,eric,yung,uli,21,17, new Date(2014,11,17),"steve@test.com"));
     gameService.insertGame(new Game(jens,yung,uli,eric,21,19, new Date(2014,11,17),"steve@test.com"));
     gameService.insertGame(new Game(jens,uli,eric,yung,26,28, new Date(2014,11,17),"steve@test.com"));
     gameService.insertGame(new Game(jens,eric,yung,uli,21,15, new Date(2014,11,17),"steve@test.com"));
     gameService.insertGame(new Game(jens,yung,eric,uli,21,19, new Date(2014,11,17),"steve@test.com"));

     gameService.insertGame(new Game(jens,eze,steve,uli,21,19, new Date(2014,11,19),"steve@test.com"));
     gameService.insertGame(new Game(jens,steve,uli,eze,14,21, new Date(2014,11,19),"steve@test.com"));
     gameService.insertGame(new Game(jens,uli,eze,steve,21,19, new Date(2014,11,19),"steve@test.com"));
     gameService.insertGame(new Game(jens,eze,steve,uli,19,21, new Date(2014,11,19),"steve@test.com"));
     gameService.insertGame(new Game(jens,steve,eze,uli,21,19, new Date(2014,11,19),"steve@test.com"));

     gameService.insertGame(new Game(uli,nick,yung,eze,21,19, new Date(2014,11,23),"steve@test.com"));
     gameService.insertGame(new Game(uli,yung,eze,nick,21,19, new Date(2014,11,23),"steve@test.com"));
     gameService.insertGame(new Game(uli,eze,nick,yung,21,19, new Date(2014,11,23),"steve@test.com"));
     gameService.insertGame(new Game(uli,nick,yung,eze,18,21, new Date(2014,11,23),"steve@test.com"));
     gameService.insertGame(new Game(uli,yung,nick,eze,21,19, new Date(2014,11,23),"steve@test.com"));
  }

  private void deleteAllData() {
    deleteAllEntity(Game.class);
     deleteAllEntity(Player.class);
  }

  private void deleteAllEntity(Class<?> clazz) {
    Query q = pm.newQuery(clazz);
    q.deletePersistentAll();
  }
}

