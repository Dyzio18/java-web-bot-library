package stdBot;

public class RemoteMapper {
    static private CollectorsFactory collectorsFactory = new CollectorsFactory();
    static private RemoteHandlersFactory remoteHandlersFactory = new RemoteHandlersFactory();
    private RemoteCollector collector;
    private RemoteHandler handler;

    public void start(String address, CollectorTypeEnum collectorTypeEnum, int maxRecursionLevel){
        handler = remoteHandlersFactory.create(address);
        collector = collectorsFactory.create(collectorTypeEnum, handler, address, maxRecursionLevel);
        collector.start();
    }

    public SiteMap getResult(){
        return collector.collectAll();
    }

}