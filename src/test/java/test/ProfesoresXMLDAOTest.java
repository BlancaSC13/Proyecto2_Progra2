package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.CursoXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.ProfesorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Profesor;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProfesoresXMLDAOTest {
    private ProfesorXMLDAO profesorXMLDAO;

    @Before
    public void init() throws IOException, JDOMException {
       ProfesorXMLDAO.crearDocumento("profesores.xml");
        profesorXMLDAO = ProfesorXMLDAO.abrirDocumento("profesores.xml");

        Profesor profesor = new Profesor("Carlos", "Boza", "P2334", "Algoritmos");
        profesorXMLDAO.insertarProfesor(profesor);
        Profesor profesor1 = new Profesor("Susana", "Rojas", "P4567", "Matemática");
        profesorXMLDAO.insertarProfesor(profesor);
        Profesor profesor2 = new Profesor("Mariana", "Ramírez", "9876", "Inglés");
        profesorXMLDAO.insertarProfesor(profesor);



        System.out.println("Impresión cursos insertados");
        ArrayList<Profesor> profesors = profesorXMLDAO.getProfesores();
        for (Iterator<Profesor> profesorActual = profesors.iterator(); profesorActual.hasNext();) {
            Profesor profesor3 = profesorActual.next();

        }

    }

    @Test
    public void probarXML(){

    }

}