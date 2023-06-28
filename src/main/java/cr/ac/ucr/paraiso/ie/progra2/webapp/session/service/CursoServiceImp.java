package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.CursoXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets.CursoServlet;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CursoServiceImp implements CursoService{
    private CursoXMLDAO cursoXMLDAO;
    @Override
    public List<Curso> listar() throws IOException, JDOMException {
        cursoXMLDAO= CursoXMLDAO.abrirDocumento("cursos.xml");
        ArrayList<Curso> cursos = cursoXMLDAO.getCursos();
        return cursos;
    }
}
