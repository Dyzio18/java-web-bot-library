/*
Library to working with HTML
https://jsoup.org/
*/


package com.company;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String testURL = "http://home.agh.edu.pl/~geoinf/pl/main/";
        Map<String, String> map = findHyperlinks(testURL);

        Stream.of(map.keySet().toString()).forEach(System.out::println);

    }


    /**
     * Function return map with hyperlinks
     */
    private static Map<String, String> findHyperlinks(String url){

        String html = "";
        try {
            html = getURLSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new TreeMap<String, String>();

        String regex="\\b(?<=(href=\"))[^\"]*?(?=\")";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(html);
        int i = 0;
        while(m.find()) {
            map.put( m.group(), url);
        }

        return map;
    }


    /**
     * Function get URL and return stringify website
     */
    public static String getURLSource(String url) throws IOException
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }

    /**
     * This function return string from HTTP response
     */
    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

}
