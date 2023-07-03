package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.CursoXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.*;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibrosXMLDAOTest {
    LibrosXMLDAO librosXMLDAO;
    @Test
    public void probarXML() throws IOException, JDOMException {
        crear();
        librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
        try {
            System.out.println(librosXMLDAO.buscarLibro("harry potter"));
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getLibros() throws IOException, JDOMException {
        librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");

        List<Libro> libros = librosXMLDAO.getLibros();
        System.out.println(libros);
    }

    @Test
    public void eliminarLibro() throws IOException, JDOMException {
        crear();
        librosXMLDAO.eliminarLibro(1);

    }

    private void crear() throws IOException {
        librosXMLDAO = LibrosXMLDAO.crearDocumento("libros.xml");
        // librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");

        List<Autor> autores = new ArrayList<>();
        autores.add( new Autor("J.k", "Rowlling"));
        autores.add( new Autor("Gabriel", "García"));

        Libro libro = new Libro("harry potter", autores,
                new Editorial("Planeta"),
                new Tematica("Ficción"));
        librosXMLDAO.insertarLibro(libro);
    }


}