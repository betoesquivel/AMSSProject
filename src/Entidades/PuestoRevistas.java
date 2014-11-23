package entidades;
import java.sql.*;
import java.io.*;

public class PuestoRevistas {

	public int id;
	public String direccion;
	private transient Conexion conn;

	public PuestoRevistas(int i, String d) {
		this.id = i;
		this.direccion = d;

	}

	public PuestoRevistas(Conexion c){
		this.conn = c;

	}

	public boolean setPuestoRevistas(PuestoRevistas a) {
		int i;
		String d;

		i = a.id;
		d = a.direccion;

		try {
			String s = "UPDATE puestoRevistas SET id = " + i + ", direccion = " + d + " WHERE id = " + i;
			conn.stmt.executeUpdate(s);
		}catch (SQLException e) {System.out.println ("Cannot update PuestoRevistas" + e); return false;}
		return true;

	}


	public PuestoRevistas getPuestoRevistas(int id){
		try {
			conn.stmt.executeQuery ("SELECT id, direccion FROM puestoRevistas WHERE id = " + id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int i = rs.getInt ("id");
				String d = rs.getString("direccion");
				rs.close();
				PuestoRevistas resultado = new PuestoRevistas(i, d);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e); }
		return null;
	}

	public boolean agregar(int i, String d){
		try {
			 String s = "INSERT INTO puestoRevistas (id, direccion)" +
				   " VALUES ("+ i + " , '" + d + " )";
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);
		}catch (Exception e) { System.out.println ("Cannot update PuestoRevistas" + e ); return false; }
		return true;
	}

	public boolean deletePuestoRevistas(int id){
		try {
			 String s = "DELETE FROM puestoRevistas WHERE id = " + id;
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);
		}catch (Exception e) { System.out.println ("Cannot delete PuestoRevistas" + e ); return false; }
		return true;
	}




}
