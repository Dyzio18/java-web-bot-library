package stdBot;

public abstract class RemoteCollector extends Thread {

    static protected MapsFactory factory = new MapsFactory();
    protected CollectableRemotely collectableRemotely = null;
    private String address;
    private RemoteHandler handler;
    protected int maxRecurtionLevel;

    public RemoteCollector(RemoteHandler handler, String address, int maxRecurtionLevel){
        this.handler = handler;
        this.address = address;
        this.maxRecurtionLevel = maxRecurtionLevel;
    }

    @Override
    public void run(){
        collectableRemotely.setEntryPoint(address);
        collectableRemotely.setMaxRecursionLevel(maxRecurtionLevel);

        while (!collectableRemotely.hasFinished())
            collectableRemotely.collectOne(handler);
    }

    public SiteMap collectAll(){

        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return collectableRemotely.getMap();
    }
}
