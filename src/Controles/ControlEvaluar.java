package controles;
import entidades.*;
import java.io.*;

public class ControlEvaluar {
   Cuenta cuenta;
   Articulo articulo;
   Consejo consejo;
   EditorJefe editorJefe;
   Juez juez;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlEvaluar(){
      conexion = new Conexion();
      cuenta = new Cuenta(conexion);
      consejo = new Consejo(conexion);
      editorJefe = new EditorJefe(conexion);
      juez = new Juez(conexion);
      articulo = new Articulo(conexion);
   }

   public boolean evaluarArticulo(String nom, int calif, String coment) {
     Articulo evaluar =  articulo.getArticulo(nom);

      Juez evaluador =  juez.getJuez(nomJ);
     return evaluar.evaluarArticulo(evaluar.id, evaluador.id, calif, coment);

   }
	
}
