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
  ControlMaestro cm;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    thisResponse = response;
    thisRequest = request;

    thisResponse.setContentType("text/html");

    //Aqui puedo empezar a preparar los templates
    out = thisResponse.getWriter();
    //Preparar el encabezado de la pagina Web de respuesta
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<TITLE>Banco AMSS</TITLE>");
    out.println(Rythm.render("<h1>hello @who!</h1>", "rythm"));
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Extraer efectivo</h3>");

    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarRegistro();
    }else if(operacion.equals("registrar")){
      registrar();
    }

  }

  public void iniciarRegistro() {
    out.println("<p>Introduzca la Cuenta</p>");
    out.println("<form method=\"GET\" action=\"Registrar\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");
    out.println("<p> Usuario  <input type=\"text\" name=\"usuario\" size=\"20\"></p>");
    out.println("<p> Contraseña  <input type=\"password\" name=\"contrasenia\" size=\"15\"></p>");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void registrar(){
    cm = new ControlMaestro();
    //La funcion trim() elimina espacios antes y despues del valor
    String usuario = thisRequest.getParameter("usuario").trim();
    String contrasenia = thisRequest.getParameter("contrasenia").trim();
    boolean existente = cm.crearCuenta(usuario, contrasenia, "sub");
    if (existente){
       out.println("<p>Gracias, ha sido registrado.</p>");

       out.println("<form method=\"GET\" action=\"menu.html\">");
       out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
    } else {
       iniciarRegistro();
    }
  }
}

