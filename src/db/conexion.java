/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author roddycorrea
 */
public class conexion {
    Connection con;
    String server = "jdbc:mysql://localhost:8889/ocw_mit_db";
    String user = "root";
    String pass = "root";
    public Connection conexion() throws ClassNotFoundException {
        try {
            //Cargamos el puente JDBC =&gt; Mysql
            Class.forName("com.mysql.jdbc.Driver");
            //Intentamos conectarnos a la base de Datos en este caso una base llamada temp
            con = DriverManager.getConnection(server, user, pass);
        } catch (SQLException ex) {
            System.out.println("Error de mysql: " + ex.getMessage());
        } 
        return con;
    }
}
