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
    public static Document doc = null;
    public static void main(String[] args) throws IOException{
        String url = "http://ocw.mit.edu/courses/new-courses/";
        int i=0;
        String link_externo = "";
        try{
		doc = Jsoup.connect(url).get();
		String title = doc.title();
		Elements links = doc.select("table.courseList tr td a[href]");
		for (Element link : links) {
			link_externo = "http://ocw.mit.edu"+link.attr("href");
                        nextPage(link_externo);
		}
            
        }catch(Exception e){
            System.out.println("error "+e);
        }
    }
    
    public static void nextPage(String link_externo){
        int i=0;
        String title = doc.title();
        System.out.println(" ---------------------------------------------- \n ");
        System.out.println("title : " + title + " " +getlectureNotes(link_externo));
        String linkCourse = getlectureNotes(link_externo);
        try {
            String html = "table tr td a[href$=.pdf]";
            System.out.println("html = "+html);
            System.out.println("linkCourse = "+linkCourse);
            doc = Jsoup.connect(linkCourse).get();
            Elements pdfs = doc.select(html);
            System.out.println(" *** "+pdfs+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Scrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" ----------------------------------------------\n\n ");
    }
    
    public static String getlectureNotes(String link_externo){
        String link = "";
        System.out.println(link_externo+"\n");
        try {
            doc = Jsoup.connect(link_externo).get();
            Elements lecture = doc.select("li");
            String texto = lecture.text();
            boolean LectNotes = encontrarLectureNotes(texto);
            if (LectNotes == true) {
                //System.out.println("encontre");
                link = link_externo+"/lecture-notes";
                System.out.println(link);
            }
        } catch (IOException ex) {
            Logger.getLogger(Scrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return link;
    }
    public static boolean encontrarLectureNotes(String texto){
        String[]palabras=texto.split(" ");
        boolean ok = false ;
        for(String i: palabras){
            if(i.equals("Lecture Notes"));{
                ok = true;
            }
        }
        return ok;
    }
    public static String lectureNotes(){
        String html = "";
        html = "table tr td a[href$=.pdf]";
        return html;
    }
    
    
}
