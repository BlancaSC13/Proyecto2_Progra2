package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface CookieService {
    Optional<Cookie> getUser(HttpServletRequest req);
}
