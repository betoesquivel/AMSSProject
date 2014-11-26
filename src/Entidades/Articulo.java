package entidades;
import java.sql.*;
import java.io.*;

public class Articulo {

	public int id;
	public String titulo;
	public String contenido;
	public Date fechaE;
	public Date fechaP;
	private transient Conexion conn;

	public Articulo(int i, String t, String c, Date e, Date p) {
		this.id = i;
		this.titulo = t;
		this.contenido = c;
		this.fechaE = e;
		this.fechaP = p;
	}

	public Articulo(Conexion c){
		this.conn = c;

	}


	public boolean setArticulo(Articulo a) {
		int i = a.id;
		String t = a.titulo;
		String c = a.contenido;
		Date fE = a.fechaE;
		Date fP = a.fechaP;

		try {
			String s = "UPDATE articulo SET id = " + i + ", titulo = " + t + ", contenido = " + c + ", fechaEscritura = " + fE + ", fechaPub = " + fP +" WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Articulo" + e); return false;}
		return true;
	}
	   public Articulo getArticulo(String t){
		try {
			conn.stmt.executeQuery ("SELECT * FROM articulo WHERE titulo = '" + t + "'");
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int nArt = rs.getInt ("id");
				String titulo = rs.getString("titulo");
				String contenido =  rs.getString("contenido");
				Date fEsc = rs.getDate("fechaEscritura");
				Date fPub = rs.getDate("fechaPub");
				rs.close();
				Articulo resultado = new Articulo(nArt, titulo, contenido, fEsc, fPub);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	   }

	   public boolean agregar(String titulo, String contenido, Date fechaPub, Date fechaEsc){
	      try {
		 String s = "INSERT INTO articulo (titulo, contenido, fechaEscritura)" +
		           " VALUES ('" + titulo + "' , '" + contenido + "', '" + fechaEsc + "' )";
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
	public boolean evaluarArticulo(int idJ, int idA, String coment, int c) {

		try {
			String s = "INSERT INTO evaluacionArticulo (idJuez, idArticulo, comentarios, evaluacion)" +
				" VALUES (" + idJ + ", " + idA + ", '" + coment + "', " + c + " )";
			System.out.println(s);
			conn.stmt.executeUpdate(s);
		} catch (SQLException e) { System.out.println ("Cannot insert evaluacion" + e); 
			return false; 
		}
			return true;
	}


}
