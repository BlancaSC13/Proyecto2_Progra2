package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TematicaServiceImp implements TematicaService{
    private TematicasXMLDAO tematicasXMLDAO;
    @Override
    public List<Tematica> listar() throws IOException, JDOMException {
        tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        ArrayList<Tematica> tematicas = tematicasXMLDAO.getTematicas();
        return tematicas;
    }
}
