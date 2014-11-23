package entidades;
import java.sql.*;

public class Guru {
   public int id;
   public String nombre;
   private transient Conexion conn;

   public Guru(Conexion co){
      this.conn = co;
   }

   public Guru(int id, String nombre){
	      this.id = id;
	      this.nombre = nombre;
	   }

   public Guru getGuru(int id){
      try {
         conn.stmt.executeQuery ("SELECT * FROM guru WHERE id = " + id);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.id = rs.getInt("id");
        	  this.nombre = rs.getString("nombre");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean setGuru(Guru gur){
      try {
    	  String s = "UPDATE guru SET nombre = " + gur.nombre + " WHERE id = " + gur.id;
    	  conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);
      return false;
      }
      return true;
   }

   public boolean addGuru(Guru gur){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO guru(nombre) VALUES('"+gur.nombre+")");
      } catch (SQLException e) {System.out.println ("Cannot addGuru()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteGuru(Guru gur){
	      try {
	    	  conn.stmt.executeUpdate ("delete from guru where id = "+gur.id);
	      } catch (SQLException e) {System.out.println ("Cannot setGuru()" + e);
	      return false;
	      }
	      return true;
	   }

}
