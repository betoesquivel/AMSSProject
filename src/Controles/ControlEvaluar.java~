package controles;
import entidades.*;
import java.io.*;

public class ControlMaestro {
   Cuenta cuenta;
   Articulo articulo;
   Consejo consejo;
   EditorJefe editorJefe;
   Juez juez;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlMaestro(){
      conexion = new Conexion();
      cuenta = new Cuenta(conexion);
      consejo = new Consejo(conexion);
      editorJefe = new EditorJefe(conexion);
      juez = new Juez(conexion);
      articulo = new Articulo(conexion);
   }

   public boolean evaluarArticulo(String nom, int calif, String coment) {
     Articulo evaluar =  articulo.getArticulo(nom);

     return evaluar.evaluarArticulo(calif, coment);

   }
	
}
