//package net.stevemoyer.vbrank.rest;
//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
//
//import java.io.IOException;
//import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//import javax.ws.rs.ext.Provider;
//
//import javax.ws.rs.*;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.*;
//import javax.ws.rs.core.Response.*;
//import javax.ws.rs.core.MediaType;
//
//import java.util.List;
//import java.util.logging.Logger;
//
////@Provider
//public class AuthorizationFilter implements ContainerRequestFilter {
//    PersistenceManager pm = PMF.getInstance().getPersistenceManager();
//    private static final Logger log = Logger.getLogger(PlayerService.class.getName());
//
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//
//        UserService userService = UserServiceFactory.getUserService();
//        User user = userService.getCurrentUser();
//
//        String emailAddress = user.getEmail();
//        Query q = pm.newQuery(Player.class);
//        q.setFilter("emailAddress == emailAddressParam");
//        q.declareParameters("String emailAddressParam");
//
//        List<Player> players = (List<Player>) q.execute(emailAddress);
//
//        if(!players.isEmpty()) {
//            return ;
//        }
//        throw new WebApplicationException(
//                Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build());
//
//    }
//}
