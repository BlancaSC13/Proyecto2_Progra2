package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface SessionService {
    Optional<String> getUser(HttpServletRequest req);
}
