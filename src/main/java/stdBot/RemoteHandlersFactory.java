package stdBot;

/**
 * This class is factory used in RemoteMapper class
 */
public class RemoteHandlersFactory {

    /**
     * This method is the main factory method utilized to create adequate objects
     * @param address This should be url website address
     * @return This returns new HTTPRemoteHandler object
     */
    public RemoteHandler create(String address){
        if (AddressUtility.isHttp(address))
            return new HttpRemoteHandler();
        return null;
    }

}
