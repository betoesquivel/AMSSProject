package entidades;
import java.sql.*;
import java.io.*;

public class Cuenta {

	public int id;
	public String usuario;
	public String contrasenia;
  public String tipo;
	public transient Conexion conn;
//  public Connection conn2;
//  public Statement stmt2;


  // Para cuando ocupo atributos de la cuenta
	public Cuenta(int i, String usr, String contra, String tipo) {
		this.id = i;
		this.usuario = usr;
		this.contrasenia = contra;
    this.tipo = tipo;
	}
  // Para cuando voy a insertar una nueva cuenta
	public Cuenta(String usr, String contra, String tipo) {
		this.usuario = usr;
		this.contrasenia = contra;
    this.tipo = tipo;
	}

  // Para cuando solamente ocupo la conexion con la db...
	public Cuenta(Conexion c){
		this.conn = c;
//      try {
//        String userName = "root";
//        String password = "";
//        String url = "jdbc:mysql://localhost/cajero";
//        Class.forName ("com.mysql.jdbc.Driver").newInstance();
//        conn2 = DriverManager.getConnection(url, userName, password);
//        stmt2 = conn2.createStatement();
//      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
	}

	public boolean setCuenta(Cuenta c) {
		int i;
		String u;
		String contra;
    String tipo;

		i = c.id;
		u = c.usuario;
		contra = c.contrasenia;
    tipo = c.tipo;

		try {
			String s = "UPDATE cuenta SET id = " + i + ", usuario = " + u + ", contrasenia = " + contra + ", tipo = " + tipo + " WHERE id = " + i;
			conn.stmt.executeUpdate(s);
		}catch (SQLException e) {System.out.println ("Cannot update Cuenta" + e); return false;}
		return true;
	}


	public Cuenta getCuenta(int id){
		try {
			conn.stmt.executeQuery ("SELECT id, usuario, contrasenia FROM cuenta WHERE id = " + id);
			ResultSet rs = conn.stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int nCuenta = rs.getInt ("id");
				String usr = rs.getString("usuario");
				String contra = rs.getString("contrasenia");
        String tipo = rs.getString("tipo");
				rs.close();
				Cuenta resultado = new Cuenta(nCuenta, usr, contra, tipo);
				return( resultado );
			}else{ return null;}
			} catch (SQLException e) {System.out.println("Excepcion en validar " + e);}
		return null;
	}

	public boolean agregar(Cuenta nuevaCuenta){
    if ( conn.stmt == null ) return false;
    try {
      String s = "INSERT INTO cuenta (usuario, contrasenia, tipo)" +
        " VALUES ('" + nuevaCuenta.usuario + "', '" + nuevaCuenta.contrasenia + "', '" + nuevaCuenta.tipo + "')";


      conn.stmt.executeUpdate(s);

    }catch (SQLException e) {
      return false;
    }catch (NullPointerException n){
      return false;
    }
		return true;
	}

	public boolean deleteCuenta(int id){
		try {
			 String s = "DELETE FROM cuenta WHERE id = " + id;
			 System.out.println(s);
			 conn.stmt.executeUpdate(s);

		}catch (SQLException e) { System.out.println ("Cannot delete Cuenta" + e ); return false;}
		return true;
	}



}
