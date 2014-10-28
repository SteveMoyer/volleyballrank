package net.stevemoyer.vbrank.rest;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/players")
public class PlayerService {
    @GET @Produces(MediaType.APPLICATION_JSON) @Path("me")
        public Player getMe() {
            return new Player("Anon2",4,2);
        }
    @GET @Produces(MediaType.APPLICATION_JSON) @Path("standings")
        public List<Player> getStanding(){
            ArrayList<Player> players =new ArrayList();
            players.add(new Player("Anon",5,1));
            players.add(new Player("Anon2", 4,2));
            players.add( new Player("Anon3",2,4));
            players.add(new Player("Anon4",1,5));
            return players;

        }
}
