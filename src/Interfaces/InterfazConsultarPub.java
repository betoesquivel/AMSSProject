package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.rythmengine.Rythm;

public class InterfazConsultarPub extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  String username;

  String pathPrefix = "/var/lib/tomcat7/webapps/EYA/templates/";
  ControlMostrar cm;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    //SETUP
     response.setContentType("text/html");  

     //VERIFICAR LOGIN
     HttpSession session=request.getSession(false);
        if(session!=null){
            username=(String)session.getAttribute("name");
        } else{
            out.println("Tienes que tener una cuenta de subscriptor valida.");
            request.getRequestDispatcher("InterfazIngresar").include(request, response);
        }

    thisResponse = response;
    thisRequest = request;

    thisResponse.setContentType("text/html");

    // variable que regresa para imprimir el html
    out = thisResponse.getWriter();

    String operacion = request.getParameter("operacion");

    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      desplegarPublicaciones();
    }else if(operacion.equals("consultar")){
      consultar();
    }
  }

  public void desplegarPublicaciones() {
    String title = "Seleccionar publicacion";
    String op = null;
    String instrucciones = "Haz click en la publicacion que deseas ver: ";

    cm = new ControlMostrar();
    ArrayList<Publicacion> pubs = getListaPublicaciones();

    out.println(Rythm.render( new File(pathPrefix + "consultarPublicaciones.html"), title, op, instrucciones, pubs));
  }

  public void consultar(){
      String title = "Registro con Exito";
      String op = "registrar";

      // Creo instancia del control 
      cm = new ControlMaestro();
      //La funcion trim() elimina espacios antes y despues del valor
      String usuario = thisRequest.getParameter("usuario").trim();
      String contrasenia = thisRequest.getParameter("contrasenia").trim();
      boolean existente = cm.crearCuenta(usuario, contrasenia, "sub");
      if (!existente){
        iniciarRegistro();
      }
      out.println(Rythm.render( new File(pathPrefix + "registro.html"), title, op, usuario) );
  }

}

