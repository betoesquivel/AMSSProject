package entidades;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class Publicacion {
	public int id;
  public Date fechaPub;
	public String titulo;
	public String tema;
	private transient Conexion conn;

	public Publicacion(int i,  Date fechaPub, String ti, String te) {
		this.id = i;
    this.fechaPub = fechaPub;
		this.titulo = ti;
		this.tema = te;
	}

	public Publicacion(int i,  Date fechaPub, String ti) {
		this.id = i;
    this.fechaPub = fechaPub;
		this.titulo = ti;
    this.tema = null;
	}

	public Publicacion(Conexion c){
		this.conn = c;
	}

	public boolean setPublicacion(Publicacion a) {
		int i;
    Date fp;
		String ti;
		String te;

		i = a.id;
    fp = a.fechaPub;
		ti = a.titulo;
		te = a.tema;

		try {
			String s = "UPDATE publicacion SET id = " + i 
        + ", fechaPub = '" + fp + "', titulo = '" + ti + "', tema = '"+ te + "' WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Publicacion" + e); return false;}
		return true;
	}


	public Publicacion getPublicacion(String t){
		try {
			conn.stmt.executeQuery ("SELECT * FROM publicacion WHERE titulo = '" + t + "'");
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
        Date fp = rs.getDate("fechaPub");
        String tit = rs.getString("titulo");
				String te = rs.getString("tema");
				rs.close();
				Publicacion resultado = new Publicacion(i,fp,  tit, te);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	}

	public boolean agregar(Date fPub, String tit){
		try {
			 String s = "INSERT INTO publicacion (fechaPub, titulo)" +
				   " VALUES ('" + fPub + "', '" + tit + "' )";
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot update Publiacion" + e ); return false;}
		return true;
	}

	public boolean deletePublicacion(int id){
		try {
			 String s = "DELETE FROM publicacion WHERE id = " + id;
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot delete Publiacion" + e ); return false;}
		return true;
	}



}
