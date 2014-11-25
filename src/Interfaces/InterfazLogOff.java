package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazLogOff extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            request.getRequestDispatcher("link.html").include(request, response);  

            HttpSession session=request.getSession();
            session.invalidate();

            out.println("Has salido del sistema con exito.");
            out.println("<a href=\"menu.html\">Ok</a>");

            out.close();
    }
}
