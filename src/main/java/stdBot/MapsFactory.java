package stdBot;

/**
 * This class represents factory which is used to create object form MapTypEnum
 * Factory is used in RemoteCollector which allows class composition
 */
public class MapsFactory {

    public CollectableRemotely create(MapTypeEnum mapTypeEnum){
        switch (mapTypeEnum){
            case SiteMap:
                return new RemoteSiteMap();
            case HyperLinksMap:
                return new RemoteHyperLinksMap();
        }
        return null;
    }
}
