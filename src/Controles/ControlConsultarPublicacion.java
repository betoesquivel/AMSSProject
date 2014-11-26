package controles;
import entidades.*;
import java.io.*;

public class ControlConsultarArticulo {
   Articulo art;
	CartaEditorJefe cej;
	Publicacion publicacion;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlConsultarArticulo(){
      conexion = new Conexion();
	art = new Articulo(conexion);
	cej = new CartaEditorJefe(conexion);
      publicacion = new Publicacion(conexion);
   }

   public String consultarArticulo(String titulo) {
     Publicacion p =  publicacion.getPublicacion(titulo);
	
	
	if(p == null) {
		return null;
	} else {
		String cartaPub = cej.getCartaPub(p.fechaPub);
		String artPub = art.getListaArticulos(p.fechaPub);
		return pub;
	}

   }
	
}
