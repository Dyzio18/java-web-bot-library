package stdBot;

public class HyperLinksCollector extends RemoteCollector {

    public HyperLinksCollector(RemoteHandler handler, String address, int maxRecurtionLevel) {
        super(handler, address, maxRecurtionLevel);
        collectableRemotely = factory.create(MapTypeEnum.HyperLinksMap);
    }
}
