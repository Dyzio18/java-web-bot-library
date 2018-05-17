package stdBot;

public class RemoteHandlersFactory {

    public RemoteHandler create(String address){
        if (AddressUtility.isHttp(address))
            return new HttpRemoteHandler();
        return null;
    }

}
