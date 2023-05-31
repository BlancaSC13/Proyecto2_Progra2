package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CookieService;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CookieServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "profesor";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService getCookie = new CookieServiceImp();
        Optional<Cookie> cookieOptional = getCookie.getUser(req);

        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Sesion ya iniciada</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Bienvenido: " + cookieOptional.get().getValue() + " inició sesión correctamente.</h1>");
                out.println("        <p><a href=\"/session-webapp/index.html\">Menu</a></p>");
                out.println("        <p><a href=\"/session-webapp/logout\">Logout</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie userCookie = new Cookie("user",username);
            resp.addCookie(userCookie);
           resp.sendRedirect(req.getContextPath()+"/login.html");

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ingreso no autorizado!");
        }
    }
}
