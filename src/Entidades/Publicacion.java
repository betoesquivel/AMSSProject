package entidades;
import java.sql.*;
import java.io.*;

public class Publicacion {
	public int id;
	public int idJefe;
	public int idCarta;
	private transient Conexion conn;

	public Publicacion(int i, int ij, int ic) {
		this.id = i;
		this.idJefe = ij;
		this.idCarta = ic;
	}

	public Publicacion(Conexion c){
		this.conn = c;

	}

	public boolean setPublicacion(Publicacion a) {
		int i;
		int ij;
		int ic;

		i = a.id;
		ij = a.idJefe;
		ic = a.idCarta;

		try {
			String s = "UPDATE publicacion SET id = " + i + ", idJefe = " + ij + ", idCarta = " + ic + " WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Publicacion" + e); return false;}
		return true;
	}


	public Publicacion getPublicacion(int id){
		try {
			conn.stmt.executeQuery ("SELECT id, idJefe, idCarta FROM publicacion WHERE id = " + id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
				int ij = rs.getInt("idJefe");
				int ic = rs.getInt("idCarta");
				rs.close();
				Publicacion resultado = new Publicacion(i, ij, ic);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	}

	public boolean agregar(int id, String nom, Date fechaUltPub){
		try {
			 String s = "INSERT INTO publicacion (id, idJefe, idCarta)" +
				   " VALUES ("+ id + " , '" + idJefe + "', " + idCarta + " )";
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
