package entidades;
import java.sql.*;
import java.io.*;

public class Subscriptor {

	public int id;
	public String nombre;
	public String tipo;
	private transient Conexion conn;

	public Subscriptor(int i, String n, String t) {
		this.id = i;
		this.nombre = n;
		this.tipo = t;
	}

	public Subscriptor(Conexion c){
		this.conn = c;

	}

	public boolean setSubscriptor(Subscriptor a) {
		int i;
		String n;
		String t;

		i = a.id;
		n = a.nombre;
		t = a.tipo;

		try {
			String s = "UPDATE subscriptor SET id = " + i + ", nombre = " + n + ", tipo = " + t +  " WHERE id = " + i;
			conn.stmt.executeUpdate(s);

		}catch (SQLException e) {System.out.println ("Cannot update Subscriptor" + e); return false;}

		return true;
	}


	public Subscriptor getSubscriptor(int id){
		try {
			conn.stmt.executeQuery ("SELECT id, nombre, tipo FROM subscriptor WHERE id = " + id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
				String n = rs.getString("nombre");
				String t = rs.getString("tipo");
				rs.close();
				Subscriptor resultado = new Subscriptor(i, n, t);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	}

	public boolean agregar(int i, String n, String t){
		try {
			 String s = "INSERT INTO subscriptor (id, nombre, tipo)" +
				   " VALUES ("+ i + " , '" + n + "', " + t + " )";
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot update Subscriptor" + e );  return false;}
		return true;
	}

	public boolean deleteSubscriptor(int id){
		try {
			 String s = "DELETE FROM subscriptor WHERE id = " + id;
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot delete Subscriptor" + e );  return false;}
		return true;
	}



}
