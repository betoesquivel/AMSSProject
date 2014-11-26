package entidades;
import java.sql.*;

public class CartaEditorJefe {
   public int id;
   public String titulo;
   public String contenido;
   public int idEditorJefe;
   public Date fechaPub;

   private transient Conexion conn;


   public CartaEditorJefe(Conexion conn){ this.conn = conn; }

   public CartaEditorJefe(int id, String titulo, String contenido, int idEditorJefe, Date fP){
      this.id = id;
      this.titulo = titulo;
      this.contenido = contenido;
      this.idEditorJefe = idEditorJefe;
      this.fechaPub = fP;
    }

   public CartaEditorJefe getCartaEditorJefe(int id){
      try {
    	 conn.stmt.executeQuery ("SELECT * FROM cartaEditorJefe WHERE id = " + id);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.id = rs.getInt("id");
           this.titulo = rs.getString("titulo");
        	 this.contenido = rs.getString("contenido");
        	  this.idEditorJefe = rs.getInt("idEditorJefe");
            this.fechaPub = rs.getDate("fechaPub");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean setCartaEditorJefe(CartaEditorJefe carta){
      try {
    	  String s = "UPDATE cartaEditorJefe SET idEditorJefe = " + carta.idEditorJefe + ", SET contenido = " + carta.contenido + " WHERE id = " + carta.id;
    	  conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);
      return false;
      }
      return true;
   }

   public boolean addCartaEditorJefe(CartaEditorJefe carta){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO cartaEditorJefe(titulo, contenido, idEditor, fechaPub) VALUES('"+carta.titulo+"','"
         +carta.contenido+"'," +carta.idEditorJefe+",'"+carta.fechaPub+"')");
      } catch (SQLException e) {System.out.println ("Cannot getCartaEditorJefe()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteCartaEditorJefe(CartaEditorJefe carta){
	      try {
	    	  conn.stmt.executeUpdate ("delete from cartaEditorJefe where id = "+carta.id);
	      } catch (SQLException e) {System.out.println ("Cannot setcartaEditorJefe()" + e);
	      return false;
	      }
	      return true;
	   }

}
