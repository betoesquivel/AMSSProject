package entidades;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Juez {
   public int id, idConsejo;
   public String nombre;
   private transient Conexion conn;

   public Juez(Conexion c){
      this.conn = c;
   }

   public Juez(int id, int idConsejo, String nombre) {
	   this.id = id;
	   this.idConsejo = idConsejo;
	   this.nombre = nombre;
   }

   public Juez getJuez(String nom) {
       try {
           conn.stmt.executeQuery ("SELECT * FROM juez WHERE nombre = '" + nom + "'");
           ResultSet rs = conn.stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
               this.id = rs.getInt ("id");
               this.idConsejo = rs.getInt ("idConsejo");
               this.nombre = rs.getString ("nombre");
               rs.close();
               return this;
           }else{ return null;}
       } catch (SQLException e) {
           System.out.println(e); return null;
       }
   }

   public boolean setJuez(Juez j) {
	   try{
		   conn.stmt.executeUpdate ("UPDATE juez SET idConsejo = " + j.idConsejo + ", nombre = " + j.nombre + " WHERE id = " + j.id);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean agregar(String usr) {
	   try{
		   conn.stmt.executeUpdate ("INSERT INTO juez (idConsejo,nombre) VALUES ('" + usr + "')");
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean deleteJuez(int id) {
	   try{
		   conn.stmt.executeUpdate ("DELETE FROM juez WHERE id = " + id);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }
}
