package net.stevemoyer.vbrank;

import java.util.Date;

import javax.jdo.PersistenceManager;

import net.stevemoyer.vbrank.rest.Game;
import net.stevemoyer.vbrank.rest.GameService;
import net.stevemoyer.vbrank.rest.Player;
import net.stevemoyer.vbrank.rest.PlayerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.google.inject.Provider;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
  private Player playera = new Player("steve@test.com","Steve");
  private Player playerb = new Player("eric@test.com","Eric");
  private Player playerc = new Player("yung@test.com","Yung");
  private Player playerd = new Player("eze@test.com","Eze");

  PersistenceManager persistenceManager ;

  Provider<PersistenceManager> provider;
  PlayerService playerService;
  GameService gameService;

  @Before
  public void setup() {
    persistenceManager = mock(PersistenceManager.class);
    provider = ProviderDouble.provide(persistenceManager);
    playerService = mock(PlayerService.class);
    gameService = new GameService(provider, playerService);
  }
  @Test
  public void shouldUpdatePlayerRecord() {
    Game game = new Game(playera,playerb,playerc,playerd,21,19,new Date(),"");
    gameService.insertGame(game);
    verify(playerService).addPlayerGame(playera,true);
    verify(playerService).addPlayerGame(playerb,true);
    verify(playerService).addPlayerGame(playerc,false);
    verify(playerService).addPlayerGame(playerd,false);
    verify(persistenceManager).makePersistent(game);
  }
}
