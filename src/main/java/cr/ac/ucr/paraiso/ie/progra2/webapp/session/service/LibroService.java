package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface LibroService {
    List<Libro> listar() throws IOException, JDOMException;
}
