package test;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.CursoXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CursoXMLDAOTest {
    private CursoXMLDAO cursoXLMDAO;

    @Before
    public void init() throws IOException, JDOMException {
        CursoXMLDAO.crearDocumento("cursos.xml");
        cursoXLMDAO = CursoXMLDAO.abrirDocumento("cursos.xml");

        Curso curso1 = new Curso(200, "Programación I", "Informática", "II");
        cursoXLMDAO.insertarCurso(curso1);

        Curso curso2 = new Curso(300, "Programación II", "Informática", "I");
        cursoXLMDAO.insertarCurso(curso2);

        Curso curso3 = new Curso(400, "Algoritmos y Estructuras de Datos", "Informática", "I");
        cursoXLMDAO.insertarCurso(curso3);

      /*  System.out.println("Impresión cursos insertados");
        ArrayList<Curso> cursos = cursoXLMDAO.getCursos();
        for (Iterator<Curso> cursoActual = cursos.iterator(); cursoActual.hasNext();) {
            Curso curso = cursoActual.next();
            System.out.println(curso.getId() + " - " + curso.getNombre());
        }*/

    }

    @Test
    public void probarXML(){

    }

    @Test
    public void eliminarXML(){
        try {
            cursoXLMDAO.eliminarCurso(400);
            System.out.println("Impresión cursos insertados");
            ArrayList<Curso> cursos = cursoXLMDAO.getCursos();
            for (Iterator<Curso> cursoActual = cursos.iterator(); cursoActual.hasNext();) {
                Curso curso = cursoActual.next();
                System.out.println(curso.getId() + " - " + curso.getNombre());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }

    }

}