package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TematicasXMLDAOTest {

    TematicasXMLDAO tematicasXMLDAO;

    @Before
    public void init() throws IOException, JDOMException {
        TematicasXMLDAO.crearDocumento("tematicas.xml");
        tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        tematicasXMLDAO.insertarTematicas(new Tematica("Ficción"));
        tematicasXMLDAO.insertarTematicas(new Tematica("Aventura"));
        tematicasXMLDAO.insertarTematicas(new Tematica("Fantasía"));
        tematicasXMLDAO.insertarTematicas(new Tematica("Misterio"));
        tematicasXMLDAO.insertarTematicas(new Tematica("Novela"));
    }

    @Test
    public void probar(){
        try {
            System.out.println(tematicasXMLDAO.buscar("Aventura"));
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }
    }


}