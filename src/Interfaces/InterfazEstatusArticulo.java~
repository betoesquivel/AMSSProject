package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazEstatusArticulo extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlAutor ca;

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
    out.println("<TITLE>AMSS</TITLE>");
    out.println("<h2>Revista EYA</h2>");


    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarEstatusArticulo();
    }else if(operacion.equals("subir")){
      verEstatusArticulo();
    }

  }

  public void iniciarEstatusArticulo() {
    out.println("<p>Introduzca la Cuenta</p>");
    out.println("<form method=\"GET\" action=\"Ingresar\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"iniciar\"/>");
    out.println("<p> Titulo del Articulo <input type=\"text\" name=\"titulo\" size=\"20\"></p>");

    out.println("<p><input type=\"submit\" value=\"Ver Estatus\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menuAutor.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void verEstatusArticulo(){
    cm = new ControlMaestro();
    //La funcion trim() elimina espacios antes y despues del valor
    String tit = thisRequest.getParameter("titulo").trim();
//    Cuenta obtenida = cm.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");

	String estatus = ca.estatusArt(u, tit); 
	
	if(!estatus.equals("")) {
       out.println("<p>Hola " + u + ", el estatus de tu articulo es el siguiente:  " + estatus + ".</p>");

       out.println("<form method=\"GET\" action=\"menuAutor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	} else {
       out.println("<p>Lo sentimos " + u + ", no se encontro tu articulo.</p>");

       out.println("<form method=\"GET\" action=\"menuAutor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	}
	

  }
}


