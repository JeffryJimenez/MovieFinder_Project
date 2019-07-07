import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;


public class Connection {

    private HttpsURLConnection con;


    public void connect(String url) throws Exception{

        org.jsoup.Connection.Response response = Jsoup.connect("https://www.google.com/search?q=hunt").followRedirects(true).execute();

        Document doc = response.parse();

        Elements elements = doc.getElementsByTag("h3");


    }
}
