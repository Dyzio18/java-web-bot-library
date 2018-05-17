package stdBot;

public class CollectorsFactory {

    RemoteCollector create(CollectorTypeEnum collectorTypeEnum, RemoteHandler handler, String address, int maxRecurtionLevel){
        switch (collectorTypeEnum){
            case Map:
                return new MapCollector(handler, address, maxRecurtionLevel);
            case HyperLinks:
                return new HyperLinksCollector(handler, address, maxRecurtionLevel);
        }
        return null;
    }

}
