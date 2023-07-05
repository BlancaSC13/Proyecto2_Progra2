package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AutoresXMLDAOTest {
    AutorXMLDAO autoresXMLDAO;


    @Before
    public void init() throws IOException, JDOMException {
        AutorXMLDAO.crearDocumento("autores.xml");
        autoresXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");

        autoresXMLDAO.insertarAutores( new Autor("J.K", "Rowilling"));
        autoresXMLDAO.insertarAutores( new Autor("Gabriel", "García"));
        autoresXMLDAO.insertarAutores( new Autor("Fran", "Kafka"));



    }

    @Test
    public void probarXML(){
      //  System.out.println(autoresXMLDAO.buscarNombre("J.k Rowilling"));
        try {
            System.out.println(autoresXMLDAO.getAutores());
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }
    }



}