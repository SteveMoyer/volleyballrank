package net.stevemoyer.vbrank.rest;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
    /**
     * Constructor.
     * */
    private PMF() {}

    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    /**
     * Returns an instance of the Persistence Manager.
     * @return persistence manager instance.
     **/
    public static PersistenceManagerFactory getInstance() {
        return pmfInstance;
    }
}
