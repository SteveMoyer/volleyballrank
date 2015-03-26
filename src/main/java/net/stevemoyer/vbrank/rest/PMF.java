package net.stevemoyer.vbrank.rest;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.HashMap;
import java.util.Map;
public final class PMF {
  /**
   * Constructor.
   * */
  private PMF() {}

  private static PersistenceManagerFactory pmfInstance;
  private static void getManagerFactory() {
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
  static {
    getManagerFactory();
  }
  /**
   * Returns an instance of the Persistence Manager.
   * @return persistence manager instance.
   **/
  public static PersistenceManagerFactory getInstance() {
    return pmfInstance;
  }
  private static PersistenceManager manager;
  public static PersistenceManager getManager() {
    if(manager==null) {
      manager = pmfInstance.getPersistenceManager();
    }
    return manager;
  }
}
