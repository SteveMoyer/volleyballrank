package net.stevemoyer.vbrank.rest;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Provider;
import com.google.inject.servlet.RequestScoped;

import java.util.HashMap;
import java.util.Map;
@RequestScoped
public final class PMF implements Provider<PersistenceManager> {
  PersistenceManagerFactory pmfInstance;
  public PMF() {
    Map<String, String> properties = new HashMap<String,String>();
    properties.put( "javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
    properties.put("javax.jdo.option.ConnectionDriverName", "org.h2.Driver");
    properties.put("javax.jdo.option.ConnectionURL", "jdbc:h2:./target/db/vb-rank");

    pmfInstance = JDOHelper.getPersistenceManagerFactory(properties);
  }
  @Override
  public PersistenceManager get() {
    System.out.println("returning a new persitence manager");
    return pmfInstance.getPersistenceManager();
  }
}
