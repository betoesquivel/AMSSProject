package entidades;

import java.sql.*;
import java.io.*;
public class Conexion {
   public Connection conn;
   public Statement stmt, stmt2;

   public Conexion(){
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/tec";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
}
