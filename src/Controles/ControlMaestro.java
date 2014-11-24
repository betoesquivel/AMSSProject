package controles;
import entidades.*;
import java.io.*;

public class ControlMaestro {
   Cuenta cuenta;
   Articulo articulo;
   Autor autor;
   AutorArticulo autorArt;
   CartaEditorJefe cartaEditor;
   Consejo consejo;
   EditorJefe editorJefe;
   Guru guru;
   Juez juez;
   Publicacion publicacion;
   PublicacionGuru guruPub;
   PuestoRevistas puestoRevistas;
   PuestoRevistasPub puestoRevistasPub;
   Subscriptor subscriptor;
   SubscriptorPub subscriptorPub;
   TemaSugerido temaSugerido;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlMaestro(){
      conexion = new Conexion();
      cuenta = new Cuenta(conexion);
      autor = new Autor(conexion);
      autorArt = new AutorArticulo(conexion);
      cartaEditor = new CartaEditorJefe(conexion);
      consejo = new Consejo(conexion);
      editorJefe = new EditorJefe(conexion);
      guru = new Guru(conexion);
      juez = new Juez(conexion);
      publicacion = new Publicacion(conexion);
      guruPub = new PublicacionGuru(conexion);
      puestoRevistas = new PuestoRevistas(conexion);
      puestoRevistasPub = new PuestoRevistasPub(conexion);
      subscriptor = new Subscriptor(conexion);
      subscriptorPub = new SubscriptorPub(conexion);
      temaSugerido = new TemaSugerido(conexion);
   }

   public boolean crearCuenta(String usr, String contrasenia, String tipo){
     Cuenta nueva = new Cuenta(usr, contrasenia, tipo);
     return cuenta.agregar(nueva);
   }
}
