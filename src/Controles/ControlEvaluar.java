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

   public boolean evaluar(String titulo, String nomJ, int calif, String coment) {
     Articulo evaluar =  articulo.getArticulo(titulo);

      Juez evaluador =  juez.getJuez(nomJ);
     return evaluar.evaluarArticulo(evaluador.id, evaluar.id, coment, calif);

   }
	
}
