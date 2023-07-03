package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutorServiceImp implements AutorService{
    private AutorXMLDAO autorXMLDAO;
    @Override
    public List<Autor> listar() throws IOException, JDOMException {
        autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
        ArrayList<Autor> autores = autorXMLDAO.getAutores();
        return autores;
    }
}
