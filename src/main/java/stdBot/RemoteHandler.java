package stdBot;

/**
 * This class is RemoteHandler interface used in composition of RemoteCollector class
 */
public interface RemoteHandler {
    void setAddress(String address);
    String getLine();
}
