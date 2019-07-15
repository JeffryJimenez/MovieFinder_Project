import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class DataBaseSearcher{

    private String movie;
    private boolean[] movieFound;

    public DataBaseSearcher(String movie){

        this.movie = movie;
        this.movieFound = new boolean[]{false,false,false};

    }

    public boolean fetch() throws Exception{

        org.jsoup.Connection.Response response = Jsoup.connect("https://alphabetizer.flap.tv/lists/list-of-every-movie-ever-made.php").followRedirects(true).execute();
        Document doc = response.parse();


        Elements list = doc.getElementsByTag("li");

        List<String> movies = new LinkedList<>();


        int counter = 0;
        for(Element element: list){

            if(counter == 68){

               movies.add(element.text());
            }

            if(counter != 68){
                counter++;
            }
        }

        list = null;

        return movies.contains(this.movie);


    }

    public boolean[] movieFinder() throws Exception{

        if(fetch()){






        }

        return this.movieFound;

    }



}
