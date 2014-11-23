package entidades;
import java.sql.*;

public class PublicacionGuru {
   public int idGuru;
   public int idPub;
   public Date fechaEnvio;
   private transient Conexion conn;

   public PublicacionGuru(Conexion conn){ this.conn = conn; }

   public PublicacionGuru(int idGuru, int idPub, Date fechaEnvio){
       this.idGuru = idGuru;
       this.idPub = idPub;
       this.fechaEnvio = fechaEnvio;
   }

   public PublicacionGuru getPublicacionGuru(int idG, int idP){
       String s = "SELECT * FROM guruPub WHERE" +
           " idGuru = " + idG + " and idPub = " + idP;
       try {
           conn.stmt.executeQuery(s);
           ResultSet rs = conn.stmt.getResultSet();
           if(rs.next()){
               this.fechaEnvio = rs.getDate("fechaEnvio");
           }else{
               return null;
           }
       }catch(SQLException e){
           System.out.println(e);
           return null;
       }
       return new PublicacionGuru(idG, idP, this.fechaEnvio);
   }

   public boolean setPublicacionGuru(PublicacionGuru pg){
       String s = "UPDATE guruPub " +
                  "SET idGuru =  " + pg.idGuru + ", idPub = " + pg.idPub +
                  ", fechaEnvio = " + pg.fechaEnvio +
                  "WHERE idGuru = " + pg.idGuru + " and idPub = " + pg.idPub;
       try {
           conn.stmt.executeUpdate(s);
       }catch(SQLException e){
           System.out.println(e);
           return false;
       }
       return true;
   }

   public boolean addPublicacionGuru(PublicacionGuru pg){
       String s = "INSERT INTO guruPub " +
                  "VALUES (" + pg.idGuru + "," + pg.idPub + "," + pg.fechaEnvio + ") ";
       try {
           conn.stmt.executeUpdate(s);
       }catch(SQLException e){
           System.out.println(e);
           return false;
       }
       return true;
   }

   public boolean deletePublicacionGuru(int idG, int idP){
       String s = "DELETE FROM guruPub " +
                  "WHERE idGuru = " + idG + " and idPub = " + idP;
       try {
           conn.stmt.executeUpdate(s);
       }catch(SQLException e){
           System.out.println(e);
           return false;
       }
       return true;
   }
}


