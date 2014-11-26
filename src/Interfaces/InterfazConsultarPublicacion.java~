package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazConsultarPublicacion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlConsultarPublicacion ccp;

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
      iniciarConsultaPublicacion();
    }else if(operacion.equals("verPub")){
      verPublicacion();
    }

  }

  public void iniciarConsultaPublicacion() {
    out.println("<p>Introduzca el titulo de la publicacion a consultar</p>");
    out.println("<form method=\"GET\" action=\"ConsultarPublicacion\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"verPub\"/>");
    out.println("<p> Titulo de la Publicacion <input type=\"text\" name=\"titulo\" size=\"50\"></p>");

    out.println("<p><input type=\"submit\" value=\"Ver Publicacion\"name=\"B1\"></p>");
    out.println("</form>");
	String t = (String) session.getAttribute("tipo");
	
	if(t.equals("sub") {
    out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
	} else if ("edi") {
    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
	}
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void verPublicacion(){
    ccp = new ControlConsultarPublicacion();
    //La funcion trim() elimina espacios antes y despues del valor
    String tit = thisRequest.getParameter("titulo").trim();
//    Cuenta obtenida = ca.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");

	String cont = ccp.consultarPublicacion(tit);
	String t = (String) session.getAttribute("tipo"); 
	
	if(!cont.equals("")) {
	       out.println("<p>" + u + ", la publicacion que solicitaste ver es la siguiente:  " + tit + ".</p>");
	       out.println("<p> A continuacion el contenido de la publicacion .</p>");
	       out.println("<p>" + cont + ".</p>");
	
		if(t.equals("sub") {
	    out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
		} else if ("edi") {
	    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
		}
	       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
	       out.println("</form>");

	       out.println("</BODY>");
	       out.println("</HTML>");
	} else {
	       out.println("<p>Lo sentimos " + u + ", no se encontro la publicacion que solicitaste ver.</p>");
	
		if(t.equals("sub") {
	    out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
		} else if ("edi") {
	    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
		}
	       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
	       out.println("</form>");

	       out.println("</BODY>");
	       out.println("</HTML>");
	}
	

  }
}


