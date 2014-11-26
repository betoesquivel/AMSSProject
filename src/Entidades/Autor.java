package entidades;
import java.sql.*;
import java.io.*;
 
public class Autor {
 
        public int id;
        public String nombre;
        public Date fechaUltPub;
        private transient Conexion conn;
 
        public Autor(int i, String nom, Date p) {
                this.id = i;
                this.nombre = nom;
                this.fechaUltPub = p;
        }
 
        public Autor(Conexion c){
                this.conn = c;
        }
 
        public boolean setAutor(Autor a) {
                int i;
                String n;
                Date f;
 
                i = a.id;
                n = a.nombre;
                f = a.fechaUltPub;
 
                try {
                        String s = "UPDATE autor SET id = " + i + ", nombre = " + n + ", fechaUltimaPublicacion = " + f+ " WHERE id = " + i;
                        conn.stmt.executeUpdate(s);
 
                }catch (SQLException e) {System.out.println ("Cannot update Autor" + e); return false;}
                return true;
        }
 
 
        public Autor getAutor(String n){
                try {
                        conn.stmt.executeQuery ("SELECT id, nombre, fechaUltimaPublicacion FROM autor WHERE nombre = '" + n + "'");
                        ResultSet rs = conn.stmt.getResultSet();
                        if (rs.next()) { //Va al primer registro si lo hay
                                int nAut = rs.getInt ("id");
                                String nom = rs.getString("nombre");
                                Date fUltPub = rs.getDate("fechaUltimaPublicacion");
                                rs.close();
                                Autor resultado = new Autor(nAut, nom, fUltPub);
                                return( resultado );
                        }else{ return null;}
                        } catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
                return null;
        }
 
        public boolean agregar(String nom, Date fechaUltPub){
                try {
                         String s = "INSERT INTO autor (nombre, fechaUltimaPublicacion)" +
                                   " VALUES ('" + nom + "', " + fechaUltPub + " )";
                         System.out.println(s);
                         conn.stmt.executeUpdate(s);
 
                }catch (SQLException e) { System.out.println ("Cannot update Autor" + e ); return false;}
                return true;
        }
 
        public boolean deleteAutor(int id){
                try {
                         String s = "DELETE FROM autor WHERE id = " + id;
                         System.out.println(s);
                         conn.stmt.executeUpdate(s);
 
                }catch (SQLException e) { System.out.println ("Cannot delete Autor" + e ); return false;}
                return true;
        }
 
 
 
}
