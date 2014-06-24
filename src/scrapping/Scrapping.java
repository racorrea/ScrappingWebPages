package scrapping;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author roddycorrea
 */
public class Scrapping {

    public static void main(String[] args){
        String url = "http://ocw.mit.edu/courses/new-courses/";
        int i=0;
        Document document;
        try {
            document = Jsoup.connect(url).get();
            Elements stats = document.select("table.courseList tbody tr ");
            Elements title = document.select("h3.deptTitle");
            Elements link = document.select("a.preview[href]");
                do{
                        System.out.println("["+i+"] *** "+title.get(i).text() + "\n" + stats.get(i).text() +"\n\n");
                    i++;
                }while(i < stats.size());
        } catch (IOException ex) {
            Logger.getLogger(Scrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
