package stdBot;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents collected site data (available routes) in form of a directed graph
 */

public class SiteMap {
    /**
     *  This field is set of every localized html link (url) under assigned starting url with selected level of recursion
     */
    public Set<String> knownInternalLinks = new HashSet<>();

    /**
     *  This field is set of every localized html route (in form of relation)
     */
    public Set<Pair<String, String>> knownRelations = new HashSet<>();

    /**
     * This methods prints every value stored in class fields with adequate header
     *
     */
    public void print() {
        System.out.println("Known links:");
        for (String link : knownInternalLinks)
            System.out.println("\t" + link);

        System.out.println("Known relations:");
        for (Pair<String, String> relation : knownRelations)
            System.out.println("\t" + relation.getKey() + " -> " + relation.getValue());
    }

    /**
     *
     * @return String This returns is literally all data extracted from assigned starting url
     */
    public String toHTML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<h3> Linki: </h3>");
        for (String link : knownInternalLinks)
            stringBuilder.append("<li><a href=\"" + link + "\">" + link + "</a></li>");

        stringBuilder.append("<h3> Relacje: </h3>");
        for (Pair<String, String> relation : knownRelations)
            stringBuilder.append("<li><a href=\"" + relation.getKey() + "\">" + relation.getKey() + "</a> --> <a href=\"" + relation.getValue() + "\">" + relation.getValue() + "</a></li>");

        return stringBuilder.toString();

    }

    /**
     * This method is printing on standard output the actual number of found internal links
     */
    public void printInternalLinksNumber() {
        System.out.println("Total number of internal links is: " + knownInternalLinks.size());
    }

    /**
     * @return int This return the actual number of found internal links as integer
     */
    public int internalLinksNumber() {
        return knownInternalLinks.size();
    }

    /**
     * This method is printing on standard output the actual number of internal relations
     */
    public void printInternalRelationNumber() {
        System.out.println("Total number of internal relations is: " + knownRelations.size());
    }

    /**
     * @return int This return the actual number of internal relations as integer
     */
    public int internalRelationsNumber() {
        return knownRelations.size();
    }

    /**
     * This method is a part of api which allows user to print site data (available routes)  in approximate graph form
     */
    public void printRelationsGraph() {
        for (Pair<String, String> relation : knownRelations) {
            printRelation(relation, 1);
        }
    }

    /**
     * This method recursively prints found data in approximate graph form
     */
    private void printRelation(Pair<String, String> relation, int level) {
        for (Pair<String, String> rel : knownRelations) {
            if (relation.getValue().equals(rel.getKey())) {
                printRelation(rel, level + 1);
                break;
            }
        }

        System.out.println(relation.getKey());
        for (Pair<String, String> rel : knownRelations) {
            if (relation.getKey().equals(rel.getKey())) {
                for(int i = 0; i < level; i++) {
                    System.out.print("\t -> ");
                }
                System.out.println(rel.getValue());
            }
        }
    }
}



