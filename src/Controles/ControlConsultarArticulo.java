package controles;
import entidades.*;
import java.io.*;

public class ControlConsultarArticulo {
   Articulo articulo;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlConsultarArticulo(){
      conexion = new Conexion();
      articulo = new Articulo(conexion);
   }

   public String consultarArticulo(String titulo) {
     Articulo consulta =  articulo.getArticulo(titulo);
    
      if(consulta == null) {
        return null;
      } else {
         return consulta.contenido;
      }

   }
	
}
