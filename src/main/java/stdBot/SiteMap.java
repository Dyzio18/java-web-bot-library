package stdBot;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SiteMap {
    public Set<String> knownInternalLinks = new HashSet<>();
    public Set<Pair<String, String>> knownRelations = new HashSet<>();

    public void print() {
        System.out.println("Known links:");
        for (String link : knownInternalLinks)
            System.out.println("\t" + link);

        System.out.println("Known relations:");
        for (Pair<String, String> relation : knownRelations)
            System.out.println("\t" + relation.getKey() + " -> " + relation.getValue());
    }

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

    public void printInternalLinksNumber() {
        System.out.println("Total number of internal links is: " + knownInternalLinks.size());
    }

    public int internalLinksNumber() {
        return knownInternalLinks.size();
    }

    public void printInternalRelationNumber() {
        System.out.println("Total number of internal relations is: " + knownRelations.size());
    }

    public int internalRelationsNumber() {
        return knownRelations.size();
    }

    public void printRelationsGraph() {
        for (Pair<String, String> relation : knownRelations) {
            printRelation(relation, 1);
        }
    }

    public void printRelation(Pair<String, String> relation, int level) {
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



