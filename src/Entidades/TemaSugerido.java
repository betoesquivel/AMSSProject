package entidades;
import java.sql.*;

public class TemaSugerido {
   public int id;
   public int idPub;
   public String tema;
   private transient Conexion conn;


   public TemaSugerido(Conexion co){
      this.conn = co;
   }

   public TemaSugerido(int id, int idPub,  String tema){
	      this.id = id;
	      this.idPub = idPub;
	      this.tema = tema;
	   }

   public TemaSugerido getTemaSugerido(int id){
      try {
    	 conn.stmt.executeQuery ("SELECT * FROM temaSugerido WHERE id = " + id);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.id = rs.getInt("id");
        	  this.idPub = rs.getInt("idPub");
        	 this.tema = rs.getString("tema");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean setTemaSugerido(TemaSugerido tema){
      try {
    	  String s = "UPDATE temaSugerido SET idPub = " + tema.idPub + ", SET tema = " + tema.tema + " WHERE id = " + tema.id;
    	  conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);
      return false;
      }
      return true;
   }

   public boolean addTemaSugerido(TemaSugerido tema){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO temaSugerido(idPub, tema) VALUES('"+tema.idPub+"','"+tema.tema+"')");
      } catch (SQLException e) {System.out.println ("Cannot getTemaSugerido()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteTemaSugerido(TemaSugerido tema){
	      try {
	    	  conn.stmt.executeUpdate ("delete from temaSugerido where id = "+tema.id);
	      } catch (SQLException e) {System.out.println ("Cannot setTemaSugerido()" + e);
	      return false;
	      }
	      return true;
	   }

}
