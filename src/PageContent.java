import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class PageContent {

    private HttpsURLConnection connection;

    public String connection(String url) throws Exception{

        URL obj = new URL(url);
        this.connection = (HttpsURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        connection.setUseCaches(false);

        //act like a browser
        connection.setRequestProperty("User-agent","Mozilla/5.0");
        connection.setRequestProperty("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        connection.setRequestProperty("Accept-Language","en-US,en;q=0.5");

        int responseCode = connection.getResponseCode();
        System.out.println("The response code is: " + responseCode);


        BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }


    public String getFormParams(String html, String username) throws Exception{

        Document document = Jsoup.parse(connection("https://www.google.com/"));

        //Google form id
       // Element loginForm = document.getElementsContainingOwnText("");
        Elements inputElements = document.getElementsByTag("input");
        List<String> paramList = new ArrayList<>();
        for(Element inputElement: inputElements){
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if(key.equals("q"))
                value = username;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
        }

        //BUILD PARAMETER LIST
        StringBuilder result = new StringBuilder();
        for(String param : paramList){
            if(result.length() == 0){
                result.append(param);
            }else{result.append("&" + param);}

        }

        return result.toString();
    }

    public void sendPost(String url, String postParams) throws Exception{

        URL obj = new URL(url);
        this.connection = (HttpsURLConnection) obj.openConnection();

        //act like a browser
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", "google.com");
        connection.setRequestProperty("User-Agent","Mozilla/5.0");
        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        connection.setRequestProperty("Accept-Language","en-US,en;q=0.5");

        connection.setRequestProperty("Connection","keep-alive");
        connection.setRequestProperty("Referer", "https://www.google.com/" );
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        connection.setDoOutput(true);
        connection.setDoInput(true);

        //Send Post Request

        DataOutputStream wr = new DataOutputStream((connection.getOutputStream()));
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        System.out.println("Post parameter" + postParams);

        BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) !=null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());




    }
}
