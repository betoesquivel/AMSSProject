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
     boolean otra = false;
     if (tipo.equals("aut")){
       otra = autor.agregar(usr,null);
     }else if (tipo.equals("sub")) {
       otra = subscriptor.agregar(usr,null);
     }else if (tipo.equals("jue")) {
       otra = juez.agregarJuez(usr);
     }else if (tipo.equals("edi")) {
       otra = editorJefe.agregar(usr);
     }
     return cuenta.agregar(nueva) && otra;
   }

    //Regresa el tipo de la cuenta, si se tuvo exito, o null si no
   public String iniciarSesion(String usr, String contr) {
       Cuenta verificar;
       verificar = cuenta.getCuenta(usr);
       if (verificar == null) return "";
       return verificar.tipo;
   }
}
