package stdBot;

import java.net.URI;
import java.net.URISyntaxException;

public class AddressUtility {

    static public boolean isHttp(String address){
        try {
            new URI(address);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    static public boolean isInternalRef(String address){
        return !address.contains(".");
    }

    static public String clearHttpAddress(String address){
        try {
            URI uri = new URI(address);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    static public String getScheme(String address){
        try {
            URI uri = new URI(address);
            return uri.getScheme();
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
