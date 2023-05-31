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
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService getCookie = new CookieServiceImp();
        Optional<Cookie> user = getCookie.getUser(req);

        if(user.isPresent()){
            Cookie userCookie = new Cookie("user","");
            userCookie.setMaxAge(0);
            resp.addCookie(userCookie);
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
