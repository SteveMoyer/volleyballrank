package net.stevemoyer.vbrank;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import net.stevemoyer.vbrank.rest.AuthServletFilter;
import net.stevemoyer.vbrank.rest.PMF;

import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;

public class VbRankModule extends ServletModule {
  PersistenceManagerFactory pmfInstance;
  public VbRankModule() {
    Map<String, String> properties = new HashMap<String,String>();
    properties.put( "javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
    properties.put("javax.jdo.option.ConnectionDriverName", "org.h2.Driver");
    properties.put("javax.jdo.option.ConnectionURL", "jdbc:h2:./target/db/vb-rank");
    properties.put("datanucleus.autoCreateSchema", "true");
    properties.put("datanucleus.autoCreateColumns", "true");
    properties.put("datanucleus.autoCreateConstraints", "true");
    properties.put("datanucleus.autoCreateTables", "true");

    pmfInstance = JDOHelper.getPersistenceManagerFactory(properties);

  }
  @Override
  protected void configureServlets() {
    bind(PersistenceManager.class).toProvider(PMF.class).in(RequestScoped.class);
    filter("/*").through(PersistenceFilter.class);
    filter("/*").through(AuthServletFilter.class);
  }
}
