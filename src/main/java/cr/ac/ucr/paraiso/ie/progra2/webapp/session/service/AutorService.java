package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface AutorService {
    List<Autor> listar() throws IOException, JDOMException;
}
