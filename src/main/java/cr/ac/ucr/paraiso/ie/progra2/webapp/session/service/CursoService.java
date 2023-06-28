package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface CursoService {
    List<Curso> listar() throws IOException, JDOMException;
}
