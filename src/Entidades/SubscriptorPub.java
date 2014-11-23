package entidades;
import java.sql.*;
import java.io.*;

public class SubscriptorPub {
   public int idSub,idPub,costoAdicional;
   public Date fechaEnvio;
   private transient Conexion conn;

   public SubscriptorPub(Conexion c){
      this.conn = c;
   }

   public SubscriptorPub(int idSub, int idPub, int costoAdicional, Date fechaEnvio) {
	   this.idSub = idSub;
	   this.idPub = idPub;
	   this.costoAdicional = costoAdicional;
	   this.fechaEnvio = fechaEnvio;
   }

   public SubscriptorPub getSubscriptorPub(int id) {
       try {
           conn.stmt.executeQuery ("SELECT * FROM subscriptorPub WHERE idSub = " + idSub + " and idPub = " + idPub);
           ResultSet rs = conn.stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
               this.idSub = rs.getInt ("idSub");
               this.idPub = rs.getInt ("idPub");
               this.costoAdicional = rs.getInt ("costoAdicional");
               this.fechaEnvio = rs.getDate ("fechaEnvio");
               rs.close();
               return this;
           }else{ return null;}
       } catch(SQLException e){
           System.out.println(e); return null;
       }
   }

   public boolean setSubscriptorPub(SubscriptorPub j) {
	   try{
		   conn.stmt.executeUpdate ("UPDATE subscriptorPub SET costoAdicional = " + j.costoAdicional + ", fechaEnvio = " + j.fechaEnvio + " WHERE idSub = " + j.idSub + " and idPub = " + j.idPub);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean insertSubscriptorPub(SubscriptorPub j) {
	   try{
		   conn.stmt.executeUpdate ("INSERT INTO subscriptorPub (idSub,idPub,costoAdicional,fechaEnvio) VALUES (" + j.idSub + "," + j.idPub + "," + j.costoAdicional + "," + j.fechaEnvio + ")");
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean deleteSubscriptorPub(int idSub, int idPub) {
	   try{
		   conn.stmt.executeUpdate ("DELETE FROM puestoRevistasPub WHERE idSub = " + idSub + " and idPub = " + idPub);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }
}
