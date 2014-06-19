/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrapping;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author utpl
 */
public class Scrapping {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args){
        String url = "http://ocw.mit.edu/courses/aeronautics-and-astronautics/16-06-principles-of-automatic-control-fall-2012/lecture-notes/";
        Document document;
        try {
            document = Jsoup.connect(url).get();
            Elements stats = document.select("table tbody tr");
            for(int i = 0; i < stats.size(); i++){
                System.out.println(stats.get(i).text());
            }
        } catch (IOException ex) {
            Logger.getLogger(Scrapping.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        
    }
    
}
