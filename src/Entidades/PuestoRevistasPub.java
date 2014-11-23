package entidades;
import java.sql.*;
import java.io.*;

public class PuestoRevistasPub {
   public int idPuesto,idPub,cantidadEnviada,cantidadRegresada;
   public Date fechaEnvio,fechaRegreso;
   private transient Conexion conn;

   public PuestoRevistasPub(Conexion c){
      this.conn = c;
   }

   public PuestoRevistasPub(int idPuesto, int idPub, int cantidadEnviada, int cantidadRegresada, Date fechaRegresada, Date fechaEnvio) {
	   this.idPuesto = idPuesto;
	   this.idPub = idPub;
	   this.fechaEnvio = fechaEnvio;
	   this.fechaRegreso = fechaRegreso;
	   this.cantidadEnviada = cantidadEnviada;
	   this.cantidadRegresada = cantidadRegresada;
   }

   public PuestoRevistasPub getPuestoRevistasPub(int id) {
       try {
           conn.stmt.executeQuery ("SELECT * FROM suscriptorPub WHERE idPuesto = " + idPuesto + " and idPub = " + idPub);
           ResultSet rs = conn.stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
               this.idPuesto = rs.getInt ("idPuesto");
               this.idPub = rs.getInt ("idPub");
               this.cantidadEnviada = rs.getInt ("cantidadEnviada");
               this.cantidadRegresada = rs.getInt ("cantidadRegresada");
               this.fechaEnvio = rs.getDate ("fechaEnvio");
               this.fechaEnvio = rs.getDate ("fechaRegreso");
               rs.close();
               return this;
           }else{ return null;}
       } catch(SQLException e) {
           System.out.println(e); return null;
       }
   }

   public boolean setPuestoRevistasPub(PuestoRevistasPub j) {
	   try{
		   conn.stmt.executeUpdate ("UPDATE suscriptorPub SET cantidadEnviada = " + j.cantidadEnviada + ", cantidadRegresada = " + j.cantidadRegresada + " fechaEnvio = " + j.fechaEnvio + " fechaRegreso = " + j.fechaRegreso + " WHERE idPuesto = " + j.idPuesto + " and idPub = " + j.idPub);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean insertPuestoRevistasPub(PuestoRevistasPub j) {
	   try{
		   conn.stmt.executeUpdate ("INSERT INTO puestoRevistasPub (idPuesto,idPub,cantidadEnviada,cantidadRegresada,fechaRegreso,fechaEnvio) VALUES (" + j.idPuesto + "," + j.idPub + "," + j.cantidadEnviada + "," + j.cantidadRegresada + "," + j.fechaEnvio + "," + j.fechaRegreso + ")");
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }

   public boolean deletePuestoRevistasPub(int idPuesto, int idPub) {
	   try{
		   conn.stmt.executeUpdate ("DELETE FROM puestoRevistasPub WHERE idPuesto = " + idPuesto + " and idPub = " + idPub);
		   return true;
	   } catch(SQLException e) {
		   return false;
	   }
   }
}
