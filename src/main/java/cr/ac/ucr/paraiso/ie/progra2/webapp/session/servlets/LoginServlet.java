package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CookieService;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CookieServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "C27403";
    final static String PASSWORD = "12345";
    final static String ROLE = "1";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionService getSession = new SessionServiceImp();
        Optional<String> sessionOptional = getSession.getUser(req);
        if (sessionOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("    <head>");
                    out.println("        <meta charset=\"UTF-8\">");
                    out.println("        <title>Sesion ya iniciada</title>");
                    out.println("    </head>");
                    out.println("    <body>");
                    out.println("        <h1>Bienvenido: " + sessionOptional.get() + " inició sesión correctamente.</h1>");
                    if (ROLE=="Admin") {
                        out.println("        <p><a href=\"/session-webapp/admin.html\">Menu</a></p>");
                    }else{
                        out.println("        <p><a href=\"/session-webapp/index.html\">Menu</a></p>");
                    }
                    out.println("        <p><a href=\"/session-webapp/logout\">Logout</a></p>");
                    out.println("    </body>");
                    out.println("</html>");
                }
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
           resp.sendRedirect(req.getContextPath()+"/login.html");

        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ingreso no autorizado!");
        }
    }
}
