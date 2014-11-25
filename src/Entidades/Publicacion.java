package entidades;
import java.sql.*;
import java.io.*;

public class Publicacion {
	public int id;
	public int idJefe;
	public int idCarta;
	public String titulo;
	public String tema;
	private transient Conexion conn;

	public Publicacion(int i, int ij, int ic, String ti, String te) {
		this.id = i;
		this.idJefe = ij;
		this.idCarta = ic;
		this.titulo = ti;
		this.tema = te;
	}

	public Publicacion(Conexion c){
		this.conn = c;

	}

	public boolean setPublicacion(Publicacion a) {
		int i;
		int ij;
		int ic;
		String ti
		String te;

		i = a.id;
		ij = a.idJefe;
		ic = a.idCarta;
		ti = a.titulo;
		te = a.tema;

		try {
			String s = "UPDATE publicacion SET id = " + i + ", idJefe = " + ij + ", idCarta = " + ic + ", titulo = " + ti + ", tema = "+ te + " WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Publicacion" + e); return false;}
		return true;
	}

	public ArrayList<Publicacion> getLista() {
		
		ArrayList<Publicacion> pub = new ArrayList<Publicacion>();
		try {
			conn.stmt.executeQuery ("SELECT * FROM publicacion");
			ResultSet rs = conn.stmt.getResultSet();
			while (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
				int ij = rs.getInt("idJefe");
				int ic = rs.getInt("idCarta");
				String ti = rs.getString("titulo");
				String te = rs.getString("tema");
				rs.close();
				Publicacion resultado = new Publicacion(i, ij, ic, ti, te);
				pub.add(resultado);
			}
			return pub;			
		} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;

	} 


	public Publicacion getPublicacion(int id){
		try {
			conn.stmt.executeQuery ("SELECT id, idJefe, idCarta FROM publicacion WHERE id = " + id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
				int ij = rs.getInt("idJefe");
				int ic = rs.getInt("idCarta");
				String ti = rs.getString("titulo");
				String t = rs.getString("tema");
				rs.close();
				Publicacion resultado = new Publicacion(i, ij, ic, ti, t);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	}

	public boolean agregar(int id, String nom, Date fechaUltPub){
		try {
			 String s = "INSERT INTO publicacion (idJefe, idCarta, tema)" +
				   " VALUES ('" + idJefe + "', " + idCarta + ", '" + tema + "'" + " )";
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
