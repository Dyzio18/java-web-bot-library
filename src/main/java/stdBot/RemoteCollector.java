package stdBot;

/**
 * This abstract class extends Thread
 * This represents composition of classes MapsFactory, CollectableRemotely, RemoteHandler
 * This class collects data
 */
public abstract class RemoteCollector extends Thread {

    static protected MapsFactory factory = new MapsFactory();
    protected CollectableRemotely collectableRemotely = null;
    private String address;
    private RemoteHandler handler;
    protected int maxRecursionLevel;

    public RemoteCollector(RemoteHandler handler, String address, int maxRecursionLevel) {
        this.handler = handler;
        this.address = address;
        this.maxRecursionLevel = maxRecursionLevel;
    }

    @Override
    public void run() {
        collectableRemotely.setEntryPoint(address);
        collectableRemotely.setMaxRecursionLevel(maxRecursionLevel);

        while (!collectableRemotely.hasFinished())
            collectableRemotely.collectOne(handler);
    }

    public SiteMap collectAll() {
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return collectableRemotely.getMap();
    }
}
