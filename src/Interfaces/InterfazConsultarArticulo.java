package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazConsultarArticulo extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlConsultarArticulo cca;

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
      iniciarConsultaArticulo();
    }else if(operacion.equals("verArticulo")){
      verArticulo();
    }

  }

  public void iniciarConsultaArticulo() {
    out.println("<p>Introduzca el articulo a consultar</p>");
    out.println("<form method=\"GET\" action=\"ConsultarArticulo\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"verArticulo\"/>");
    out.println("<p> Titulo del Articulo <input type=\"text\" name=\"titulo\" size=\"20\"></p>");

    out.println("<p><input type=\"submit\" value=\"Ver Articulo\"name=\"B1\"></p>");
    out.println("</form>");
        HttpSession session = thisRequest.getSession();
    String t = (String) session.getAttribute("tipo");
	
	if(t.equals("jue")) {
    out.println("<form method=\"GET\" action=\"menuJuez.html\">");
	} else if (t.equals("edi")) {
    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
	}
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void verArticulo(){
    cca = new ControlConsultarArticulo();
    //La funcion trim() elimina espacios antes y despues del valor
    String tit = thisRequest.getParameter("titulo").trim();
//    Cuenta obtenida = ca.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");
        String t = (String) session.getAttribute("tipo");

	String cont = cca.consultarArticulo(tit);
	
	if(!cont.equals("")) {
	       out.println("<p>" + u + ", el articulo que solicitaste ver es el siguiente:  " + tit + ".</p>");
	       out.println("<p> A continuacion el contenido del articulo .</p>");
	       out.println("<p>" + cont + ".</p>");
	
		if(t.equals("jue")) {
	    out.println("<form method=\"GET\" action=\"menuJuez.html\">");
		} else if (t.equals("edi")) {
	    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
		}
	       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
	       out.println("</form>");

	       out.println("</BODY>");
	       out.println("</HTML>");
	} else {
	       out.println("<p>Lo sentimos " + u + ", no se encontro el articulo que solicitaste ver.</p>");
	
		if(t.equals("jue")) {
	    out.println("<form method=\"GET\" action=\"menuJuez.html\">");
		} else if (t.equals("edi")) {
	    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
		}
	       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
	       out.println("</form>");

	       out.println("</BODY>");
	       out.println("</HTML>");
	}
	

  }
}


