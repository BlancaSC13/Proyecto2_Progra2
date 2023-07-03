package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibroServiceImp implements LibroService{
    private LibrosXMLDAO librosXMLDAO;
    @Override
    public List<Libro> listar() throws IOException, JDOMException {
        librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
        ArrayList<Libro> libros = librosXMLDAO.getLibros();
        return libros;
    }
}
