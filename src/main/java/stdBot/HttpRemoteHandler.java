package stdBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class allows to connect with remote server
 */
public class HttpRemoteHandler implements RemoteHandler {

    /**
     * This filed is URL address in a form of string
     */
    private String address = "";
    private Boolean addressHasChanged = false;
    private URL _url = null;
    private BufferedReader pageReader;

    @Override
    public void setAddress(String address) {
        this.address = address;
        addressHasChanged = true;
    }

    @Override
    public String getLine() {
        try {
            if (addressHasChanged)
                connect();
            return pageReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * This method connects to the website under assigned address in class field (url) - address
     * @throws IOException
     */
    private void connect() throws IOException {
        _url = new URL(address);
        URLConnection connection = _url.openConnection();
        pageReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        addressHasChanged = false;
    }
}
