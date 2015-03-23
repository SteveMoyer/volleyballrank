package net.stevemoyer.vbrank.rest;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

public class AuthServletFilter implements Filter {
  PersistenceManager pm = PMF.getInstance().getPersistenceManager();
  private static final Logger log = Logger.getLogger(PlayerService.class.getName());

  public void doFilter(final ServletRequest request,
      final ServletResponse response,
      FilterChain chain)
    throws java.io.IOException, javax.servlet.ServletException {
    String url = ((HttpServletRequest)request).getRequestURL().toString();
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if(userService.isUserAdmin()) {
      chain.doFilter(request,response);
      return;
    }
    String emailAddress = user.getEmail();
    Query q = pm.newQuery(Player.class);
    q.setFilter("emailAddress == emailAddressParam");
    q.declareParameters("String emailAddressParam");
    q.setUnique(true);
    log.severe(url + " emailAddress:" + emailAddress);
    Object o = q.execute(emailAddress);
    Player player = (Player)o; 

    if(player != null) {
      log.severe(url + " player not null: " + player);
      chain.doFilter(request,response);
    }

    log.severe(url + " player null");
    ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_FORBIDDEN);

  }
  public void init(FilterConfig filterConfig)
    throws ServletException {
  }
  public void destroy() {
  }
} 
