package controles;
import entidades.*;
import java.io.*;
import java.sql.Date;

public class ControlCrearPublicacion {
   Cuenta cuenta;
   Articulo articulo;
	Publicacion publicacion;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlCrearPublicacion(){
      conexion = new Conexion();
      articulo = new Articulo(conexion);
      cuenta = new Cuenta(conexion);
	publicacion = new Publicacion(conexion);

   }

	public boolean crearPublicacion(String titulo, Date fpub) {
		
		boolean exitosa = publicacion.agregar(fpub, titulo);

		return exitosa;

	}

	public boolean publicarArticulo(String titulo, Date fpub) {
		
		boolean publicado = articulo.pubArticulo(titulo, fpub);

		return publicado;

	}


}
