package stdBot;

import java.net.URI;
import java.net.URISyntaxException;

public class AddressUtility {

    /**
     * This method check and return true if address is correct HTTP URL
     * in other cases return false
     *
     * @param String address
     * @return boolean
     */
    static public boolean isHttp(String address){
        try {
            new URI(address);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    /**
     * This method check internal references for address
     *
     * @param String address
     * @return boolean
     */
    static public boolean isInternalRef(String address){
        return !address.contains(".");
    }

    /**
     * This method can replace adresses startet to www. on domain address URL
     *
     * @param String address
     * @return String
     */
    static public String clearHttpAddress(String address){
        try {
            URI uri = new URI(address);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    /**
     * This method return correct Uniform Resource Identifier (URI) address or null as exception
     *
     * @param String address
     * @return String
     */
    static public String getScheme(String address){
        try {
            URI uri = new URI(address);
            return uri.getScheme();
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
