package scrapping;

import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import static org.apache.tika.metadata.Property.ValueType.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import test.test;

/**
 *
 * @author roddycorrea
 */
public class Scrapping {
    public static Document doc = null;
    public static void main(String[] args) throws IOException{
        String url = "http://ocw.mit.edu/courses/";
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
        String departamento = extractTitle(title);
        String linkCourse = getlectureNotes(link_externo);
        String nombre_archivo = "";
        String metadata = "";
        String link_file = "";
        String file = "";
        String data_recurso = "";
        String pdfs_data ="";
        try {
            String html = "table tr td a[href$=.pdf]";
            System.out.println("html = "+html);
            System.out.println("linkCourse = "+linkCourse);
            doc = Jsoup.connect(linkCourse).get();
            Elements pdfs = doc.select(html);
             pdfs_data = pdfs.toString();
             data_recurso = extractNro(pdfs_data);
             file = metadata_link(pdfs_data);
             link_file = "http://ocw.mit.edu"+file;
             metadata = metadata(link_file);
             metadata = "application/pdf";
            nombre_archivo = extractNro2(data_recurso);
            System.out.println(" *** "+pdfs+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Scrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        test tst = new test();
        tst.testInsert(departamento, nombre_archivo, title, link_externo, linkCourse, metadata);
    }
    public static String metadata_link(String pdfs_data){
        StringTokenizer st =new StringTokenizer(pdfs_data, "\"");
        String file="";
        int contador = 1;
            while (st.hasMoreElements()) {
                String token = st.nextElement().toString();
                if (contador == 2) {
                    file = token;
                }
                contador ++;
            }
        return file;
    }
    public static String metadata(String link_file){
        String url = link_file;
        String tipo_archivo="";
        Tika tika = new Tika();
        Metadata metadata = new Metadata();
        try {
            String mediaType = tika.detect(new URL(url));
            System.out.println(mediaType);
            for(String name : metadata.names()) {
                String value = metadata.get(name);
                tipo_archivo = value;
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return tipo_archivo;
    }
    public static String extractNro(String pdfs_data){
        StringTokenizer st =new StringTokenizer(pdfs_data, "/");
        String file="";
        int contador = 1;
            while (st.hasMoreElements()) {
                String token = st.nextElement().toString();
                if (contador == 6) {
                    file = token;
                }
                contador ++;
            }
        return file;
    }
    public static String extractNro2(String file){
        StringTokenizer st =new StringTokenizer(file, "\"");
        String fil="";
        int contador = 1;
            while (st.hasMoreElements()) {
                String token = st.nextElement().toString();
                if (contador == 1) {
                    fil = token;
                }
                contador ++;
            }
        return fil;
    }
    public static String extractTitle(String title){
        StringTokenizer st =new StringTokenizer(title, "|");
        String tit="";
        int contador = 1;
            while (st.hasMoreElements()) {
                String token = st.nextElement().toString();
                if (contador == 2) {
                    tit = token;
                }
                contador ++;
            }
        return tit;
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
