package stdBot;

/**
 * This class implements CollectableRemotely interface
 */
public class RemoteHyperLinksMap implements CollectableRemotely {
    @Override
    public void setEntryPoint(String address) {

    }

    @Override
    public void collectOne(RemoteHandler handler) {
    }

    @Override
    public SiteMap getMap() {
        return null;
    }

    @Override
    public boolean hasFinished() {
        return false;
    }

    @Override
    public void setMaxRecursionLevel(int level) {
    }
}
