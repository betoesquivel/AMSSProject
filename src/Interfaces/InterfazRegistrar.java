package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.rythmengine.Rythm;

public class InterfazRegistrar extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  String pathPrefix = "/var/lib/tomcat7/webapps/EYA/templates/";
  ControlMaestro cm;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    thisResponse = response;
    thisRequest = request;

    thisResponse.setContentType("text/html");

    // variable que regresa para imprimir el html
    out = thisResponse.getWriter();

    //Preparar variable con parametros para el template
    // Se agregararn valores con params.put("nombreVar", "valor");
    // Se actualizan variables con params.put("nombreVar", "nuevoValor");
    Map<String, Object> params = new HashMap<String, Object>();
    String operacion = request.getParameter("operacion");

    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarRegistro();
    }else if(operacion.equals("registrar")){
      registrar();
    }
  }

  public void iniciarRegistro() {
    String title = "Registrar nueva Cuenta";
    String op = null;
    out.println(Rythm.render( new File(pathPrefix + "registro.html"), title, op) );
  }

  public void registrar(){
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

