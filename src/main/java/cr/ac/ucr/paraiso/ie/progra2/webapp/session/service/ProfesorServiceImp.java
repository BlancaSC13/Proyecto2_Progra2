package cr.ac.ucr.paraiso.ie.progra2.webapp.session.service;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.ProfesorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Profesor;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfesorServiceImp implements ProfesorService{
    private ProfesorXMLDAO profesorXMLDAO;

    @Override
    public List<Profesor> listar() throws IOException, JDOMException {
         profesorXMLDAO = ProfesorXMLDAO.abrirDocumento("profesores.xml");
        ArrayList<Profesor> profesors = profesorXMLDAO.getProfesores();
        return profesors;
    }
}
