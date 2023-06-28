package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Profesor;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/profesores")
public class ProfesorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfesorService service = new ProfesorServiceImp();
        List<Profesor> profesors = null;
        try {
            profesors = service.listar();
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

        SessionService getSession = new SessionServiceImp();
        Optional<String> sessionOptional = getSession.getUser(req);

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de Cursos</title>");
            out.println("    </head>");
            out.println("    <body>");
            if(sessionOptional.isPresent()) {
                out.println("        <h1>Listado de Profesores!</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Nombre</th>");
                out.println("<th>Apellidos</th>");
                out.println("<th>Carnet</th>");
                out.println("<th>Curso</th>");
                out.println("</tr>");
                profesors.forEach(c -> {
                    out.println("<tr>");
                    out.println("<td>" + c.getNombre() + "</td>");
                    out.println("<td>" + c.getApellidos() + "</td>");
                    out.println("<td>" + c.getCarnet() + "</td>");
                    out.println("<td>" + c.getCurso() + "</td>");
                    out.println("</tr>");
                });
                out.println("</table>");
                out.println("        <p><a href=\"/session-webapp/admin.html\">Menu</a></p>");
            }else{
                out.println("        <h1>Debe hacer login para ver los profesores!</h1>");
                out.println("        <p><a href=\"/session-webapp/login.html\">Login</a></p>");
            }
            out.println("    </body>");
            out.println("</html>");
        }
    }
}
