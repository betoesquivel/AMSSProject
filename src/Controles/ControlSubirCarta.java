package controles;
import entidades.*;
import java.io.*;
import java.sql.Date;

public class ControlSubirCarta {
   CartaEditorJefe cartaEditor;
   EditorJefe editor;

   public transient Conexion conexion;

   //Prepara la conexion que comparte con las entidades
   public ControlSubirCarta(){
      conexion = new Conexion();
      cartaEditor = new CartaEditorJefe(conexion);
		editor = new EditorJefe(conexion);
   }

	public boolean subirCarta(String usr, String titulo, String contenido) {
		EditorJefe editorJef = editor.getEditorJefe(usr);
		boolean cartaSub = cartaEditor.agregar(titulo, contenido, editorJef.id);

		return cartaSub;

	}

}
