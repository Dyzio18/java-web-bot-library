package stdBot;

/**
 * This class collects site data (routes map) and returns it as Object of type SiteMap
 */
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

    /**
     * @return SiteMap This returns the collected result of scanning site under url assigned at the beginning
     */
    public SiteMap getResult(){
        return collector.collectAll();
    }

}
