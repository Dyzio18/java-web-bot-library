package stdBot;

public interface CollectableRemotely {
    void setEntryPoint(String address);
    void collectOne(RemoteHandler handler);
    SiteMap getMap();
    boolean hasFinished();
    void setMaxRecursionLevel(int level);

}
