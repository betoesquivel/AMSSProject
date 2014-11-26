package entidades;
import java.sql.*;
import java.io.*;

public class EditorJefe {
   public int id;
   public String nombre;
   private transient Conexion conn;

   public EditorJefe(Conexion c){
      this.conn = c;
   }

   public EditorJefe(int id, String nombre) {
	   this.id = id;
	   this.nombre = nombre;
   }

   public EditorJefe getEditorJefe(int id) {
       try {
           conn.stmt.executeQuery ("SELECT * FROM editorJefe WHERE id = " + id);
           ResultSet rs = conn.stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
               this.id = rs.getInt ("id");
               this.nombre = rs.getString ("nombre");
               rs.close();
               return this;
           }else{ return null;}
       }catch (SQLException e) {
           System.out.println(e); return null;
       }
   }

   public boolean setEditorJefe(EditorJefe j) {
	   try{
		   conn.stmt.executeUpdate ("UPDATE editorJefe SET nombre = '"+  j.nombre + "' WHERE id = " + j.id);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean agregar(String usr) {
	   try{
		   conn.stmt.executeUpdate ("INSERT INTO editorJefe (nombre) VALUES ('" + usr + "')");
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean deleteEditorJefe(int id) {
	   try{
		   conn.stmt.executeUpdate ("DELETE FROM editorJefe WHERE id = " + id);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }
}
