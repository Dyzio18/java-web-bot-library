package stdBot;

/**
 * This class represents MapCollector which inherits from RemoteCollector
 * Object used in composition of RemoteCollector
 */
public class MapCollector extends RemoteCollector {

    public MapCollector(RemoteHandler handler, String address, int maxRecursionLevel) {
        super(handler, address, maxRecursionLevel);
        collectableRemotely = factory.create(MapTypeEnum.SiteMap);
    }
}
