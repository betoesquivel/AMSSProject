package controles;
import entidades.*;
import java.io.*;

public class ControlSubscripcion {
   Cuenta cuenta;
   Subscripcion subscripcion;
   Subscriptor subscriptor;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlSubscripcion(){
      conexion = new Conexion();
      subscripcion = new Subscripcion(conexion);
      cuenta = new Cuenta(conexion);
      subscriptor = new Subscriptor(conexion);
   }

	public boolean contratarSubscripcion(String usr, int anios) {
    Subscriptor s = subscriptor.getSubscriptor(usr);
    if (s == null) return false;
    return subscripcion.contratarSubscripcion(s, anios);
	}


	public boolean renovarSubscripcion(String usr, int anios) {
    Subscriptor s = subscriptor.getSubscriptor(usr);
    if (s == null) return false;
    return subscripcion.renovarSubscripcion(s, anios);
	}

}
