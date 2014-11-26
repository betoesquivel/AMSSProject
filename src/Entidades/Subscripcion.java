package entidades;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Subscripcion {
   public int id;
   public int idSubscriptor;
   public int anios;
   public double total;
   public java.sql.Date fechaInicio;
   private transient Conexion conn;


   public Subscripcion(Conexion conn){ this.conn = conn; }

   public Subscripcion(int id, int idS,  int a, double t, java.sql.Date f){
      this.id = id;
      this.idSubscriptor = idS;
      this.anios = a;
      this.total = t;
      this.fechaInicio = f;
    }

   public Subscripcion getSubscripcion(int id){
      try {
    	 conn.stmt.executeQuery ("SELECT * FROM subscripcion WHERE id = " + id);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
        	 this.id = rs.getInt("id");
        	  this.idSubscriptor = rs.getInt("idSubscriptor");
        	 this.anios = rs.getInt("anios");
           this.total = rs.getDouble("total");
           this.fechaInicio = rs.getDate("fechaInicio");
            rs.close();
            return(this);
         }else{ return null;}
      } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
      return null;
   }

   public boolean setSubscripcion(Subscripcion sub){
      try {
    	  String s = "UPDATE subscripcion SET idSubscriptor = " + sub.idSubscriptor + ", anios = " + sub.anios + " WHERE id = " + sub.id;
    	  conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);
      return false;
      }
      return true;
   }

   public boolean addSubscripcion(Subscripcion sub){
      try {
    	  conn.stmt.executeUpdate ("INSERT INTO subscripcion(idSubscriptor, anios, total, fechaInicio) VALUES("+sub.idSubscriptor+","+sub.anios
            +","+sub.total+",'"+sub.fechaInicio+"')");
      } catch (SQLException e) {System.out.println ("Cannot getSubscripcion()" + e);
      return false;
      }
      return true;
   }

   public boolean deleteSubscripcion(Subscripcion sub){
	      try {
	    	  conn.stmt.executeUpdate ("delete from subscripcion where id = "+sub.id);
	      } catch (SQLException e) {System.out.println ("Cannot setsubscripcion()" + e);
	      return false;
	      }
	      return true;
	   }

	//renueva la suscripcion de dicho subscriptor, añadiendo las cant de años a la columna de anios actuales
	public boolean renovarSubscripcion(Subscriptor subscriptor, int cant_anios){
		int anios = 0;
		try {
			//se obtienen los años de suscripcion
			conn.stmt.executeQuery ("SELECT anios FROM subscripcion where idSubscriptor = " + subscriptor.id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				anios = rs.getInt ("anios");
				anios += cant_anios;
				rs.close();
			} else {
				//no tiene suscripcion
			}
			 String s = "UPDATE subscripcion SET anios = "+anios+" where idSubscriptor = "+subscriptor.id+" )";
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot update Subscriptor" + e );  return false;}
		return true;
	}
	
	public boolean contratarSubscripcion(Subscriptor subscriptor, int anios){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fechaInicio = new Date();
		double precio = 1000;
		double total = 0;
		for (int i = 1; i <= anios; i++ ){
			if (precio - (precio*.25*(i-1)) > 0){
				total += precio - (precio*.25*(i-1));
			}
		}
//		System.out.println("fecha de inicio "+format.format(fechaInicio));
//		System.out.println("total "+total);
		try {
			 String s = "INSERT INTO subscripcion (idSubscriptor, anios, total, fechaInicio)" +
				   " VALUES ("+ subscriptor.id + " , " + anios + ", " + total +" , "+ format.format(fechaInicio) + " )";
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot add Subscripcion" + e );  return false;}
		return true;

  }

}
