package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.sql.Date;

public class InterfazSubirArticulo extends HttpServlet {
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
      iniciarSubirArticulo();
    }else if(operacion.equals("subir")){
      subirArticulo();
    }

  }

  public void iniciarSubirArticulo() {
    out.println("<p>Introduzca la Cuenta</p>");
    out.println("<form method=\"GET\" action=\"SubirArticulo\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"subir\"/>");
    out.println("<p> Titulo  <input type=\"text\" name=\"titulo\" size=\"20\"></p>");
    out.println("<p> Contenido  <input type=\"textarea\" name=\"contenido\" ></p>");
    out.println("<p> Fecha Escrito (yyyy-mm-dd)  <input type=\"text\" name=\"fechaEscrito\" size=\"20\"></p>");

    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menuAutor.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void subirArticulo(){
    ca = new ControlAutor();
    //La funcion trim() elimina espacios antes y despues del valor
    String tit = thisRequest.getParameter("titulo").trim();
    String contr = thisRequest.getParameter("contenido").trim();
    Date fechaEsc = Date.valueOf(thisRequest.getParameter("fechaEscrito").trim());
    Date fechaPub = null; 
//    Cuenta obtenida = cm.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");

	boolean exito = ca.subirArt(u, tit, contr, fechaEsc, fechaPub); 
	
	if(exito) {
       out.println("<p>Gracias " + u + ", exito al subir articulo.</p>");

       out.println("<form method=\"GET\" action=\"menuAutor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	} else {
       out.println("<p>Lo sentimos " + u + ", no se pudo subir tu articulo, intentalo mas tarde.</p>");

       out.println("<form method=\"GET\" action=\"menuAutor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");		
	}

  }
}


