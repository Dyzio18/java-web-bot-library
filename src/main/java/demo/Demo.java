
import com.sun.deploy.net.URLEncoder;
import demo.Common;
import stdBot.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Scanner;

public class Demo extends Thread {

    private final int PORT = 2222;
    private static String css;
    private static String html;
    private static String res;
    private static SiteMap siteMap;
    private static RemoteMapper remoteMapper;



    public static void main(String[] args) {
        Demo gtp = new Demo();
        Common helper = new Common();
        Demo.res = "...";
        Demo.css = helper.readFile(helper.cssPath);
        Demo.html = helper.readFile(helper.htmlPath);


        Demo.remoteMapper = new RemoteMapper();
        //Demo.remoteMapper.start("http://www.wggios.agh.edu.pl/", CollectorTypeEnum.Map, 1);
        //Demo.siteMap = remoteMapper.getResult();
        //Demo.res = siteMap.toHTML();

        gtp.start();
    }
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("MiniServer active "+PORT);
            boolean shudown = true;

            while (shudown) {
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(is));


                String line;
                line = in.readLine();
                String auxLine = line;
                line = "";
                // POST data
                int postDataI = -1;
                while ((line = in.readLine()) != null && (line.length() != 0)) {
                    System.out.println(line);
                    if (line.indexOf("Content-Length:") > -1) {
                        postDataI = new Integer(line
                                .substring(
                                        line.indexOf("Content-Length:") + 16,
                                        line.length())).intValue();
                    }
                }
                String postData = "-----";
                for (int i = 0; i < postDataI; i++) {
                    int intParser = in.read();
                    postData += (char) intParser;
                }

                String urlAddr = "";
                if(postData.length() > 5) {
                    urlAddr = postData.toString().substring(10);
                    urlAddr = java.net.URLDecoder.decode(urlAddr, "UTF-8");

                }

                Demo.remoteMapper.start(urlAddr, CollectorTypeEnum.Map, 1);
                Demo.siteMap = Demo.remoteMapper.getResult();
                Demo.res = Demo.siteMap.toHTML();

                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html; charset=utf-8");
                out.println("Server: MINISERVER");
                out.println("");

                // Send the HTML page
                out.println("<style>"+Demo.css+"</style>");
                out.println(""+Demo.html+"<section class=\"content\">");
                //out.println("<H4>GET->"+auxLine+ "</H4>");
                out.println("<h2> Mapa dla strony </h2><p>"+ urlAddr +"</p>");

                out.println("<ul class=\"list\">"+Demo.res+"</ul>");
                out.println("</section></body>");

                if(auxLine.indexOf("?shutdown")>-1){
                    shudown = false;
                }
                out.close();
                socket.close();
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}