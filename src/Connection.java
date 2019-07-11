import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.util.*;


public class Connection {

    private HttpsURLConnection con;
    Hashtable<String, String> linksNames = new Hashtable<>();



    public void connect(String url) throws Exception{

        org.jsoup.Connection.Response response = Jsoup.connect("https://www.google.com/search?q=hunt").followRedirects(true).execute();
        Document doc = response.parse();
        Elements elements = doc.getElementsByClass("LC20lb");
        Elements linksHref = doc.getElementsByClass("iUh30");

        List<String> links = new ArrayList<>();
        List<String> linksHreflist = new ArrayList<>();

        for(Element element : elements){

            links.add(element.text());

        }

        for(Element element: linksHref){
            linksHreflist.add(element.text());

        }


        String[] titles = new String[links.size()];
        String[] linksArray = new String[linksHreflist.size()];

        if(titles.length == linksArray.length){

            int i = 0;
            int j = 0 ;

            for(String element: links){

                titles[i] = element;
                i++;

            }

            for (String element: linksHreflist){
                linksArray[j] = element;
                j++;
            }

        }

        for(int i = 0; i < titles.length; i++){
            this.linksNames.put(titles[i],linksArray[i]);
        }

    }
}
