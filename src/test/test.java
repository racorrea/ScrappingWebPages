/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import db.conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import scrapping.Scrapping;

/**
 *
 * @author utpl
 */
public class test {
    public static void main(String[] args) {
        //testInsert("wwwwww", "qqqqqqq", "eeeeeeee", "rrrrrrr", "tttttt", "yyyyyyyy");
        scrapping.Scrapping sc = new Scrapping();
//        String cadena[] = sc.cortarCadena("Unified Engineering I, II, III, & IV | Aeronautics and Astronautics | MIT OpenCourseWare");
//        for (int i = 0; i < cadena.length; i++) {
//                System.out.println(cadena[i]);
//        }
        String data = "<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/01_contents_cvr.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/02_part0.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/03_part1a_to_CMS.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/04_part1b.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/05_part1c.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/06_part1d.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/07_part2a.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/08_part2b.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/09_part2c.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/10_part3.pdf\">PDF</a>\n" +
"<a href=\"/courses/aeronautics-and-astronautics/16-050-thermal-energy-fall-2002/lecture-notes/11_index.pdf\">PDF</a>\n" +
"";
        String file = sc.metadata_link(data);
        String link_file = "http://ocw.mit.edu"+file;
        String metadata = sc.metadata(link_file);
        System.out.println(metadata);
        //System.out.println(sc.extractNro2(file));
        
        
        
        
    }
    public void testInsert (String departamento,String nombre_archivo,String nombre_curso,String url_curso,String url_recursos,String tipo_archivo){
        conexion db = new conexion();
        
        double retorna = 0;
        String sql="INSERT INTO data(departamento, nombre_archivo, nombre_curso, url_curso, url_recursos, tipo_archivo) VALUES ('"+departamento+"', '"+nombre_archivo+"', '"+nombre_curso+"', '"+url_curso+"', '"+url_recursos+"', '"+tipo_archivo+"')";
        System.out.println(sql);
        Connection dbCon = null;
        try {
            dbCon = db.conexion();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        try{
            Statement s = dbCon.createStatement();
            s.execute(sql);
            
        }catch(Exception ex){
            System.out.println("Excepcion = "+ex);
        }
    }
}
