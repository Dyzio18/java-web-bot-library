package stdBot;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class SiteMap {
    public Set<String> knownInternalLinks = new HashSet<>();
    public Set<Pair<String, String>> knownRelations = new HashSet<>();

    public void print(){
        System.out.println("Known links:");
        for (String link: knownInternalLinks)
            System.out.println("\t" + link);

        System.out.println("Known relations:");
        for (Pair<String, String> relation: knownRelations)
            System.out.println("\t" + relation.getKey() + " -> " + relation.getValue());
    }

    public String toHTML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<h3> Linki: </h3>");
        for (String link: knownInternalLinks)
            stringBuilder.append("<li><a href=\""+link+"\">"+link+"</a></li>");

        stringBuilder.append("<h3> Relacje: </h3>");
        for (Pair<String, String> relation: knownRelations)
            stringBuilder.append("<li><a href=\""+relation.getKey() +"\">"+relation.getKey() +"</a> --> <a href=\""+relation.getValue()+"\">"+relation.getValue()+"</a></li>");

        return stringBuilder.toString();

    }
}
