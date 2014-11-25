package entidades;
import java.sql.*;
import java.io.*;

public class Articulo {

	public int id;
	public String titulo;
	public String contenido;
	public Date fechaP;
	public Date fechaE;
	private transient Conexion conn;

	public Articulo(int i, String t, String c, Date p, Date e) {
		this.id = i;
		this.titulo = t;
		this.contenido = c;
		this.fechaP = p;
		this.fechaE = e;
	}

	public Articulo(Conexion c){
		this.conn = c;

	}


	public boolean setArticulo(Articulo a) {
		int i = a.id;
		String t = a.titulo;
		String c = a.contenido;
		Date fP = a.fechaP;
		Date fE = a.fechaE;

		try {
			String s = "UPDATE articulo SET id = " + i + ", titulo = " + t + ", contenido = " + c + ", fechaPublicacion = " + fP + ", fechaEscritura = " + fE +" WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Articulo" + e); return false;}
		return true;
	}
	   public Articulo getArticulo(String t){
		try {
			conn.stmt.executeQuery ("SELECT id, titulo, contenido, fechaPublicacion, FechaEscritura FROM articulo WHERE titulo = '" + t + "'");
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int nArt = rs.getInt ("id");
				String titulo = rs.getString("titulo");
				String contenido =  rs.getString("contenido");
				Date fPub = rs.getDate("fechaPub");
				Date fEsc = rs.getDate("fechaEsc");
				rs.close();
				Articulo resultado = new Articulo(nArt, titulo, contenido, fPub, fEsc);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	   }

	   public boolean agregar(String titulo, String contenido, Date fechaPub, Date fechaEsc){
	      try {
		 String s = "INSERT INTO articulo (titulo, contenido, fechaPublicacion, FechaEscritura)" +
		           " VALUES ('" + titulo + " , '" + contenido + "', " + fechaPub + "', " + fechaEsc + " )";
		 System.out.println(s);
		 conn.stmt.executeUpdate(s);

	      }catch (SQLException e) { System.out.println ("Cannot update Articulo" + e ); return false;}
		return true;
	   }

	public boolean deleteArticulo(int id){
	      try {
		 String s = "DELETE FROM articulo WHERE id = " + id;
		 System.out.println(s);
		 conn.stmt.executeUpdate(s);

	      }catch (SQLException e) { System.out.println ("Cannot delete Articulo" + e ); return false;}
		return true;
	   }
	public boolean evaluarArticulo(int idA, int idJ, int c, String coment) {

		try {
			String s = "INSERT INTO evaluacionArticulo (idArticulo, idJuez, comentarios, evaluacion)" +
				" VALUES (" + idA + ", " + idJ + ", '" + coment + "', " + c + " )";
			System.out.println(s);
			conn.stmt.executeUpdate(s);
		} catch (SQLException e) { System.out.println ("Cannot insert evaluacion" + e); return false; }
			return true;
	}


}
