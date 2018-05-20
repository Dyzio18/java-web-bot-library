package stdBot;

/**
 * This is CollectableRemotely interface which is used in
 *      RemoteSiteMap
 *      RemoteHyperLinksMap
 *      RemoteCollection
 *      MapFactory
 *
 * which allows to use class composition
 */
public interface CollectableRemotely {
    void setEntryPoint(String address);

    /**
     * Method of the interface
     * @param handler This parameter is used to set adequate RemoteHandler
     */
    void collectOne(RemoteHandler handler);

    /**
     * Method of the interface
     * @return SiteMap This returns the final search result
     */
    SiteMap getMap();

    boolean hasFinished();

    /**
     * Method of the interface
     * @param level This param sets the maximum depth of recursion search
     */
    void setMaxRecursionLevel(int level);
}
