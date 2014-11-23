package entidades;
import java.sql.*;

public class Consejo {
   public int id;
   public String nombre;
   public Date anio;
   private transient Conexion conn;

   public Consejo(Conexion co){
      this.conn = co;
   }

   public Consejo(int id, String nombre, Date anio){
	      this.id = id;
	      this.nombre = nombre;
	      this.anio = anio;
	   }

   public Consejo getConsejo(int id){
      try {
    	 conn.stmt.executeQuery ("SELECT * FROM consejo WHERE id = " + id);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.id = rs.getInt("id");
        	  this.nombre = rs.getString("nombre");
        	 this.anio = rs.getDate("anio");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean setConsejo(Consejo con){
      try {
    	  String s = "UPDATE consejo SET nombre = " + con.nombre + ", SET anio = " + con.anio + " WHERE id = " + con.id;
    	  conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);
      return false;
      }
      return true;
   }

   public boolean addConsejo(Consejo con){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO consejo(nombre, anio) VALUES('"+con.nombre+"','"+con.anio+"')");
      } catch (SQLException e) {System.out.println ("Cannot getConsejo()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteConsejo(Consejo con){
	      try {
	    	  conn.stmt.executeUpdate ("delete from consejo where id = "+con.id);
	      } catch (SQLException e) {System.out.println ("Cannot setConsejo()" + e);
	      return false;
	      }
	      return true;
	   }

}
