package entidades;
import java.sql.*;

public class AutorArticulo {
   public int idAutor;
   public int idArticulo;
   private transient Conexion conn;


   public AutorArticulo(Conexion co){ this.conn = co; }

   public AutorArticulo(int idAutor, int idArticulo){
	      this.idAutor = idAutor;
	      this.idArticulo = idArticulo;
	   }

   public AutorArticulo getAutorArticulo(int idAutor, int idArticulo){
      try {
    	 conn.stmt.executeQuery ("SELECT * FROM autorArticulo WHERE idAutor = " + idAutor +" AND idArticulo = "+idArticulo);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.idArticulo = rs.getInt("idArticulo");
        	  this.idAutor = rs.getInt("idAutor");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean addAutorArticulo(AutorArticulo aa){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO autorArticulo(idAutor, idArticulo) VALUES('"+aa.idAutor+"','"+aa.idArticulo+"')");
      } catch (SQLException e) {System.out.println ("Cannot getAutorArticulo()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteAutorArticulo(AutorArticulo aa){
	      try {
	    	  conn.stmt.executeUpdate ("delete from autorArticulo where idAutor = "+aa.idAutor+" AND idArticulo = "+aa.idArticulo);
	      } catch (SQLException e) {System.out.println ("Cannot setAutorArticulo()" + e);
	      return false;
	      }
	      return true;
	   }

}
