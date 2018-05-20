package stdBot;

/**
 * This class call fabric for create maps
 */
public class MapCollector extends RemoteCollector {

    public MapCollector(RemoteHandler handler, String address, int maxRecurtionLevel) {
        super(handler, address, maxRecurtionLevel);
        collectableRemotely = factory.create(MapTypeEnum.SiteMap);
    }
}
