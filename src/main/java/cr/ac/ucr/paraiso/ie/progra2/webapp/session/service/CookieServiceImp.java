package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class CookieServiceImp implements CookieService{

    @Override
    public Optional<Cookie> getUser(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Arrays.stream(cookies).filter(c -> "user".equals(c.getName())).findAny();
    }
}
