package controles;
import entidades.*;
import java.io.*;

public class ControlConsultarPublicacion {
   Articulo art;
	CartaEditorJefe cej;
	Publicacion publicacion;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlConsultarPublicacion(){
      conexion = new Conexion();
      art = new Articulo(conexion);
      cej = new CartaEditorJefe(conexion);
      publicacion = new Publicacion(conexion);
   }

   public String consultarPublicacion(String titulo) {
      Publicacion p =  publicacion.getPublicacion(titulo);
      if(p == null) {
        return null;
      } else {
        String cartaPub = cej.getCartaPub(p.fechaPub);
        String artPub = art.getListaArtPublicacion(p.fechaPub);
        String pub = cartaPub + "\n" +  artPub;
        return pub;
      }

   }
	
}
