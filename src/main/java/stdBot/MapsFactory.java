package stdBot;


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
