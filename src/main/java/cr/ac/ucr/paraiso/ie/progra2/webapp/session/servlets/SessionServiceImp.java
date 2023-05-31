package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import jakarta.batch.runtime.context.StepContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class SessionServiceImp implements SessionService {
    @Override
    public Optional<String> getUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user!=null){
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
