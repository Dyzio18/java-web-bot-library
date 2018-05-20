package demo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Common {

    public String htmlPath;
    public  String cssPath;

    public Common(){
        this.cssPath = ".\\src\\main\\java\\demo\\resources\\style.css";
        this.htmlPath = ".\\src\\main\\java\\demo\\resources\\index.html";

    }

    public  String readFile(String filePath)
    {
        String everything = "";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  everything;

    }
}
