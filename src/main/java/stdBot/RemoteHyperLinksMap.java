package stdBot;

/**
 * This class implements CollectableRemotely interface
 */
public class RemoteHyperLinksMap implements CollectableRemotely {
    /**
     *
     * @param address
     */
    @Override
    public void setEntryPoint(String address) {

    }

    /**
     *
     * @param handler This parameter is used to set adequate RemoteHandler
     */
    @Override
    public void collectOne(RemoteHandler handler) {
    }

    /**
     *
     * @return This returns null
     */
    @Override
    public SiteMap getMap() {
        return null;
    }

    /**
     *
     * @return This returns predefined boolean which is false
     */
    @Override
    public boolean hasFinished() {
        return false;
    }

    /**
     *
     * @param level This param sets the maximum depth of recursion search
     */
    @Override
    public void setMaxRecursionLevel(int level) {
    }
}
