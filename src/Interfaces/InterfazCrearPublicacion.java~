package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.sql.Date;

public class InterfazCrearPublicacion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlCrearPublicacion ccp;

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
      iniciarCrearPublicacion();
    }else if(operacion.equals("crearPub")){
      crearPublicacion();
    }

  }

  public void iniciarCrearPublicacion() {
    out.println("<p>Introduzca el listado del </p>");
    out.println("<form method=\"GET\" action=\"CrearPublicacion\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"crearPub\"/>");
    out.println("<p> Titulo Publicacion  <input type=\"text\" name=\"tPub\" size=\"20\"></p>");
    out.println("<p> Fecha de Publicacion (yyyy-mm-dd)  <input type=\"text\" name=\"fPub\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 1  <input type=\"text\" name=\"tArt1\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 2  <input type=\"text\" name=\"tArt2\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 3  <input type=\"text\" name=\"tArt3\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 4  <input type=\"text\" name=\"tArt4\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 5  <input type=\"text\" name=\"tArt5\" size=\"20\"></p>");
    out.println("<p> Titulo Articulo 6  <input type=\"text\" name=\"tArt6\" size=\"20\"></p>");
    out.println("<p> Titulo Carta Editor  <input type=\"text\" name=\"cartaEdit\" size=\"20\"></p>");

    out.println("<p><input type=\"submit\" value=\"Crear\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void crearPublicacion(){
    ccp = new ControlCrearPublicacion();
    //La funcion trim() elimina espacios antes y despues del valor
    String tpub = thisRequest.getParameter("tPub").trim();
    String tit1 = thisRequest.getParameter("tArt1").trim();
    String tit2 = thisRequest.getParameter("tArt2").trim();
    String tit3 = thisRequest.getParameter("tArt3").trim();
    String tit4 = thisRequest.getParameter("tArt4").trim();
    String tit5 = thisRequest.getParameter("tArt5").trim();
    String tit6 = thisRequest.getParameter("tArt6").trim();
//    String titCarta = thisRequest.getParameter("cartaEdit").trim();
    Date fpub = Date.valueOf(thisRequest.getParameter("fPub").trim());

    Date fechaPub = null; 
//    Cuenta obtenida = cm.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");
	
	boolean tp = ccp.crearPublicacion(tpub, fpub);
	boolean a1 = ccp.publicarArticulo(tit1, fpub);
	boolean a2 = ccp.publicarArticulo(tit2, fpub);
	boolean a3 = ccp.publicarArticulo(tit3, fpub);
	boolean a4 = ccp.publicarArticulo(tit4, fpub);
	boolean a5 = ccp.publicarArticulo(tit5, fpub);
	boolean a6 = ccp.publicarArticulo(tit6, fpub);

	
	if(a1 && a2 && a3 && a4 && a5 && a6 && tp) {
       out.println("<p>Gracias " + u + ", la creacion de la publicacion fue exitosa.</p>");

       out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	} else {
       out.println("<p>Lo sentimos " + u + ", no se pudo crear la publicacion, intentalo mas tarde.</p>");

       out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");		
	}

  }
}


