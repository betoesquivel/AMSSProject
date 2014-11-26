package controles;
import entidades.*;
import java.io.*;
import java.sql.Date;

public class ControlCrearPublicacion {
   Cuenta cuenta;
   Articulo articulo;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlAutor(){
      conexion = new Conexion();
      articulo = new Articulo(conexion);
      cuenta = new Cuenta(conexion);

   }

	public boolean publicarArticulo(String titulo, Date fpub) {
		Articulo art = articulo.getArticulo(titulo);
		
		boolean publicado = art.pubArticulo(titulo, fpub);

		return publicado;

	}


}
