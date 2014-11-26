package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazRenovarSub extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlSubscripcion cs;

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
      iniciarRenovarSubscripcion();
    }else if(operacion.equals("renovar")){
      renovar();
    }

  }

  public void iniciarRenovarSubscripcion() {
    out.println("<p>Introduzca la cantidad de anios a renovar:</p>");
    out.println("<form method=\"GET\" action=\"RenovarSub\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"renovar\"/>");
    out.println("<p> Anios <input type=\"range\" max=\"5\" min=\"1\" name=\"anios\"></p>");

    out.println("<p><input type=\"submit\" value=\"Renovar Subscripcion\"name=\"B1\"></p>");
    out.println("<p>Se realizara el cargo correspondiente en mi cuenta.</p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void renovar(){
    cs = new ControlSubscripcion();
    //La funcion trim() elimina espacios antes y despues del valor
    int anios = Integer.parseInt(thisRequest.getParameter("anios").trim());
//    Cuenta obtenida = ca.iniciarSesion(usuario, contrasenia, "sub");
        HttpSession session = thisRequest.getSession();
        String u = (String) session.getAttribute("user");

	//boolean compra = cs.renovarSubscripcion(u, anios); 
  boolean compra = true;
	
	if(compra) {
       out.println("<p>Hola " + u + ", la renovacion fue exitosa.</p>");
       out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	} else {
       out.println("<p>Lo sentimos " + u + ", no se pudo completar el pago.</p>");

       out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
       out.println("<p><input type=\"submit\" value=\"Regresar Menu\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
	}
	

  }
}


