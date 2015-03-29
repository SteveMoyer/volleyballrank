package net.stevemoyer.vbrank;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.stevemoyer.vbrank.rest.PMF;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class PersistenceFilter implements Filter {

  private final Provider<PersistenceManager> pmf;

  @Inject
  public PersistenceFilter(Provider<PersistenceManager> pmf) {
    this.pmf = pmf;
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    PersistenceManager pm = pmf.get();
    try {
      chain.doFilter(request, response);
    } finally {
    System.out.println("closing a persitence manager");
      pm.close();
    System.out.println("closed a persitence manager");

    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }
}
