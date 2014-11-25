package controles;
import entidades.*;
import java.io.*;
import java.util.*;

public class ControlMostrar {
   Cuenta cuenta;
   CartaEditorJefe cartaEditor;
   Publicacion publicacion;
   Articulo articulo;
   EditorJefe editorJefe;


   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlMostrar(){
      conexion = new Conexion();
      cuenta = new Cuenta(conexion);
      cartaEditor = new CartaEditorJefe(conexion);
      publicacion = new Publicacion(conexion);
      editorJefe = new EditorJefe(conexion);
      articulo = new Articulo(conexion);
   }

   public ArrayList<Publicacion> getListaPublicaciones() { 
	ArrayList<Publicacion>  p = publicacion.getLista();
	
	return p;
	
   }

   public String obtenerTemaPublic (int id){
     Publicacion p = publicacion.getPublicacion(id);
     return p.tema;
   }

   public String cartaEditor (int id) {
	Publicacion p = publicacion.getPublicacion(id);
	CartaEditorJefe cej = cartaEditor.getCartaEditorJefe(p.idCarta);
	
	return cej.contenido;
   }

   
}
