package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface TematicaService {
    List<Tematica> listar() throws IOException, JDOMException;
}
