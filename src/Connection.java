import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.util.*;


public class Connection {

    private HttpsURLConnection con;
    private Hashtable<String, String> linksNames = new Hashtable<>();
    private String movie;
    private StringBuffer link;
    private String[] sites;
    private boolean[] found;

    public Connection(String movie){
        this.movie = movie;
        this.link = new StringBuffer("https://www.google.com/search?q=");
        this.sites = new String[]{"Hulu","Netflix"};
        this.found = new boolean[]{false,false};
    }



    public void connect() throws Exception{

        for(int k = 0; k < this.sites.length; k++) {
            linkFormer(k);                                                                          //have link reset*************************************************

            String link = this.link.toString();

            org.jsoup.Connection.Response response = Jsoup.connect(link).followRedirects(true).execute();
            Document doc = response.parse();
            Elements elements = doc.getElementsByClass("LC20lb");
            Elements linksHref = doc.getElementsByClass("iUh30");

            List<String> links = new ArrayList<>();
            List<String> linksHreflist = new ArrayList<>();

            for (Element element : elements) {

                links.add(element.text());

            }

            for (Element element : linksHref) {
                linksHreflist.add(element.text());

            }


            String[] titles = new String[links.size()];
            String[] linksArray = new String[linksHreflist.size()];

            if (titles.length == linksArray.length) {

                int i = 0;
                int j = 0;

                for (String element : links) {

                    titles[i] = element;
                    i++;

                }

                for (String element : linksHreflist) {
                    linksArray[j] = element;
                    j++;
                }

            }

            for (int i = 0; i < titles.length; i++) {
                this.linksNames.put(titles[i].toLowerCase(), linksArray[i]);
            }

            if(this.linksNames.containsKey("watch " + this.movie.toLowerCase() + " streaming online | hulu (free trial)")){
                found[0] = true;
            }
            if(this.linksNames.containsKey(this.movie.toLowerCase() + " | netflix")){
                found[1] = true;
            }



        }

        int q = 0;

    }


    private void linkFormer(int index){

        for(int i = 0; i < this.movie.length(); i++){


            if(this.movie.charAt(i) == ' '){
                this.link.append("+");
            }
            else {
                this.link.append(this.movie.charAt(i));
            }

        }

        this.link.append("+");
        this.link.append(sites[index]);

    }
}
