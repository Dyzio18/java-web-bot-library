package stdBot;

import javafx.util.Pair;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements CollectableRemotely interface and collect links stored in class fields
 * which are links (urls) Queues
 */
public class RemoteSiteMap implements CollectableRemotely {
    private Queue<String> currentLinks = new ArrayDeque<>();
    private Queue<String> nextLinks = new ArrayDeque<>();
    private SiteMap siteMap = new SiteMap();
    private int currentRecursionLevel = 0;
    private int maxRecursionLevel = 0;
    private Pattern pattern = Pattern.compile("href=\".*?\"");
    private String domain;

    @Override
    public void setEntryPoint(String address) {
        currentLinks.add(address);
        domain = AddressUtility.clearHttpAddress(address);
    }

    @Override
    public void collectOne(RemoteHandler handler) {
        String address = getNextAddress();

        enterSite(handler, address);

        StringBuilder siteContent = new StringBuilder();
        String line;

        while ((line = handler.getLine()) != null){
            siteContent.append(line);
        }

        Matcher matcher = pattern.matcher(siteContent.toString());

        String newLink;

        while (matcher.find()) {
            newLink = matcher.group().substring(6);
            newLink = newLink.replaceAll("\"", "");

            if (AddressUtility.isInternalRef(newLink)){
                newLink = domain + "/" + newLink;
                newLink = newLink.replaceAll("/+", "/");
                newLink = AddressUtility.getScheme(address) + "://" + newLink;
            }

            if (newLink.contains(domain) && !siteMap.knownInternalLinks.contains(newLink)){
                siteMap.knownInternalLinks.add(newLink);
                siteMap.knownRelations.add(new Pair<>(address, newLink));
                nextLinks.add(newLink);
            }
        }
    }

    /**
     * This method is inherited from interface CollectableRemotely and it returns result of search
     * @return SiteMap This returns the result of page search in the form of SiteMap class object.
     */
    @Override
    public SiteMap getMap() {
        return siteMap;
    }

    /**
     * This method is inherited from interface CollectableRemotely and it indicates the end of page in-depth search
     * @return boolean This returns the boolean which indicated the end of page search
     */
    @Override
    public boolean hasFinished() {
        return currentRecursionLevel > maxRecursionLevel || (currentLinks.isEmpty() && nextLinks.isEmpty());
    }

    /**
     * This method is inherited from interface CollectableRemotely and it sets the recursion level of site searching
     * @param level This parameter is integer which sets the maximum depth of website search
     */
    @Override
    public void setMaxRecursionLevel(int level) {
        maxRecursionLevel = level;
    }

    /**
     * This method enter the site under assigned address passed in certain parameter
     * @param handler This parameter is used handler
     * @param address This parameter is site address (url)
     */
    private void enterSite(RemoteHandler handler, String address){
        handler.setAddress(address);
    }

    /**
     * This method move along queue of currentLinks Queue (in default state returns currentLinks Queue head)
     * @return String This returns currentLinks Queue head
     */
    private String getNextAddress(){
        if (currentLinks.isEmpty()){
            currentLinks = nextLinks;
            nextLinks = new ArrayDeque<>();
            currentRecursionLevel++;
        }
        return currentLinks.poll();
    }
}
