package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazEvaluarArticulo extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlAutor ce;

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
      iniciarEvaluarArticulo();
    }else if(operacion.equals("evaluar")){
      evaluacionArticulo();
    }

  }

  public void iniciarEvaluarArticulo() {
    out.println("<p>Introduzca la Cuenta</p>");
    out.println("<form method=\"GET\" action=\"EvaluarArticulo\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"evaluar\"/>");
    out.println("<p> Titulo del Articulo <input type=\"text\" name=\"titulo\" size=\"20\"></p>");
    out.println("<p> Calificacion <input type=\"text\" name=\"calif\" size=\"5\"></p>");
    out.println("<p> Comentarios <input type=\"text\" name=\"comments\" size=\"45\"></p>");

    out.println("<p><input type=\"submit\" value=\"Evaluar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menuJuez.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void evaluacionArticulo(){
    ce = new ControlEvaluar();
    //La funcion trim() elimina espacios antes y despues del valor
    String titulo = thisRequest.getParameter("titulo").trim();
    int calificacion = thisRequest.getParameter("calif").trim();
    String comentarios = thisRequest.getParameter("comments").trim();

//    Cuenta obtenida = cm.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");

	boolean evaluado = ce.evaluar(titulo, u, calificacion, comentarios); 
	
	if(evaluado) {
       out.println("<p>" + u + ", has evaluado el articulo:  " + titulo + ".</p>");

       out.println("<form method=\"GET\" action=\"menuJuez.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");

	} else {

       out.println("<p>" + u + ", no se pudo registrar tu evaluacion para el articulo: " + titulo + ".</p>");

       out.println("<form method=\"GET\" action=\"menuJuez.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	}
	

  }
}


