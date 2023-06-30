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


/*    @BeforeEach
    public void init() throws IOException, JDOMException {
       *//* LibrosXMLDAO.crearDocumento("libros.xml");
        librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");

        List<Autor> autores = new ArrayList<>();
        autores.add( new Autor(1, "J.k", "Rowlling"));
        autores.add( new Autor(2, "Gabriel", "García"));

        Libro libro = new Libro(1, "harry potter", autores,
                new Editorial(0, "Planeta"),
                new Tematica(0001, "Ficción"));
        librosXMLDAO.insertarLibro(libro);*//*

    }*/

    @Test
    public void probarXML(){
        try {
            System.out.println(librosXMLDAO.buscarLibro("harry potter"));
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getLibros() throws IOException, JDOMException {
       List<Libro> libros = librosXMLDAO.getLibros();
        System.out.println(libros);
    }

    @Test
    public void eliminarLibro() throws IOException, JDOMException {
        librosXMLDAO = LibrosXMLDAO.crearDocumento("libros.xml");
       // librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");

        List<Autor> autores = new ArrayList<>();
        autores.add( new Autor(1, "J.k", "Rowlling"));
        autores.add( new Autor(2, "Gabriel", "García"));

        Libro libro = new Libro(1, "harry potter", autores,
                new Editorial(0, "Planeta"),
                new Tematica(01, "Ficción"));
        librosXMLDAO.insertarLibro(libro);

        librosXMLDAO.eliminarLibro(1);

    }


}