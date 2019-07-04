import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class PageContent {


    public String connection(String url) throws Exception{

        URL obj = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

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


    public String getFormParams(String html, String username, String password) throws Exception{

        Document document = Jsoup.parse(connection("https://www.google.com/"));

        //Google form id
        Element loginForm = document.getElementById("lga");
        Elements inputElements = loginForm.getElementsByTag("input");
        List<String> paramList = new ArrayList<>();
        for(Element inputElement: inputElements){
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if(key.equals("Email"))
                value = username;
            else if(key.equals("passwd"))
                value = password;
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
}
