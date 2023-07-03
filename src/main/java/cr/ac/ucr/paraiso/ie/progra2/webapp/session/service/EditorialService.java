package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface EditorialService {
    List<Editorial> listar() throws IOException, JDOMException;
}
