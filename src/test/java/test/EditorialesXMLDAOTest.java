package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class EditorialesXMLDAOTest {

        EditorialesXMLDAO editorialesXMLDAO;

    @Before
    public void init() throws IOException, JDOMException {
        EditorialesXMLDAO.crearDocumento("editoriales.xml");
        editorialesXMLDAO=EditorialesXMLDAO.abrirDocumento("editoriales.xml");
        editorialesXMLDAO.insertarEditoriales(new Editorial("Planeta"));
        editorialesXMLDAO.insertarEditoriales(new Editorial("HarperCollins"));
        editorialesXMLDAO.insertarEditoriales(new Editorial("Penguin Random House"));
        editorialesXMLDAO.insertarEditoriales(new Editorial("Simon & Schuster"));
        editorialesXMLDAO.insertarEditoriales(new Editorial("Wiley"));
    }

    @Test
    public void probar() throws DataConversionException {
        System.out.println(editorialesXMLDAO.buscar(5));

    }


}