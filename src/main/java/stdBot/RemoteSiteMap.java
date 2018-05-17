package stdBot;

import javafx.util.Pair;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteSiteMap implements CollectableRemotely {
    private Queue<String> currentLinks = new ArrayDeque<>();
    private Queue<String> nextLinks = new ArrayDeque<>();
    private SiteMap siteMap = new SiteMap();
    private int currentRecurtionLevel = 0;
    private int maxRecurtionLevel = 0;
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

    @Override
    public SiteMap getMap() {
        return siteMap;
    }

    @Override
    public boolean hasFinished() {
        return currentRecurtionLevel > maxRecurtionLevel || (currentLinks.isEmpty() && nextLinks.isEmpty());
    }

    @Override
    public void setMaxRecursionLevel(int level) {
        maxRecurtionLevel = level;
    }

    private void enterSite(RemoteHandler handler, String address){
        handler.setAddress(address);
    }

    private String getNextAddress(){
        if (currentLinks.isEmpty()){
            currentLinks = nextLinks;
            nextLinks = new ArrayDeque<>();
            currentRecurtionLevel++;
        }
        return currentLinks.poll();
    }
}
