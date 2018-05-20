package stdBot;

/**
 * This class call fabric for create hyperlinks
 */
public class HyperLinksCollector extends RemoteCollector {

    public HyperLinksCollector(RemoteHandler handler, String address, int maxRecursionLevel) {
        super(handler, address, maxRecursionLevel);
        collectableRemotely = factory.create(MapTypeEnum.HyperLinksMap);
    }
}
