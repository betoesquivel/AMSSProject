package controles;
import entidades.*;
import java.io.*;

public class ControlConsultarArticulo {
   Articulo publicacion;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlConsultarArticulo(){
      conexion = new Conexion();
      publicacion = new Publicacion(conexion);
   }

   public String consultarArticulo(String titulo) {
     Publicacion p =  publicacion.getPublicacion(titulo);
	
	
	if(p == null) {
		return null;
	} else {
		String pub = p.getListaArticulos(p.fechaPub);
		return pub;
	}

   }
	
}
