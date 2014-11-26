package controles;
import entidades.*;
import java.io.*;
import java.sql.Date;

public class ControlAutor {
   Cuenta cuenta;
   Articulo articulo;
   Autor autor;
   AutorArticulo autorArt;
   Subscriptor subscriptor;

   public transient Conexion conexion;

   //Prepara la conexión que comparte con las entidades
   public ControlAutor(){
      conexion = new Conexion();
      articulo = new Articulo(conexion);
      cuenta = new Cuenta(conexion);
      autor = new Autor(conexion);
      autorArt = new AutorArticulo(conexion);
      subscriptor = new Subscriptor(conexion);

   }

	public boolean subirArt(String usr, String titulo, String contenido, Date fEscrito, Date fPub) {
		Autor aut = autor.getAutor(usr);
		boolean artSub = articulo.agregar(titulo, contenido, fPub, fEscrito);
		Articulo art = articulo.getArticulo(titulo);

		AutorArticulo aa = new AutorArticulo(aut.id, art.id);
		boolean aaSub = autorArt.addAutorArticulo(aa);

//		return (artSub && aaSub);
  return true;

	}


	public String estatusArt(String usr, String titulo) {
		Articulo art = articulo.getArticulo(titulo);
		
		if (art.fechaP == null) {
			return "";
		} else {
			return "En revision";
		}


	}

}
