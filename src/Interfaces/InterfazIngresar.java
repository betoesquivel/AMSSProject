package interfaces;
import controles.*;
import entidades.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazIngresar extends HttpServlet {
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
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Extraer efectivo</h3>");

    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarIngresar();
    }else if(operacion.equals("iniciar")){
      iniciar();
    }

  }

  public void iniciarIngresar() {
    out.println("<p>Introduzca la Cuenta</p>");
    out.println("<form method=\"GET\" action=\"Ingresar\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"iniciar\"/>");
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

  public void iniciar(){
    cm = new ControlMaestro();
    //La funcion trim() elimina espacios antes y despues del valor
    String usuario = thisRequest.getParameter("usuario").trim();
    String contrasenia = thisRequest.getParameter("contrasenia").trim();
    String tipo = cm.iniciarSesion(usuario, contrasenia);
    if (!tipo.equals("")){
        HttpSession session = thisRequest.getSession();
        session.setAttribute("user", usuario);
        session.setAttribute("tipo", tipo);

        String u = (String) session.getAttribute("user");
       out.println("<p>Bienvenido " + u + ", ha sido loggeado.</p>");

       if (tipo.equals("aut")){
         out.println("<form method=\"GET\" action=\"menuAutor.html\">");
       }else if (tipo.equals("sub")) {
         out.println("<form method=\"GET\" action=\"menuSubscriptor.html\">");
       }else if (tipo.equals("jue")) {
         out.println("<form method=\"GET\" action=\"menuJuez.html\">");
       }else if (tipo.equals("edi")) {
         out.println("<form method=\"GET\" action=\"menuEditorJefe.html\">");
       }
       out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</BODY>");
       out.println("</HTML>");
    } else {
       iniciarIngresar();
    }
  }
}


