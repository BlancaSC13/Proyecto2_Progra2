package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Profesor;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public ProfesorXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static ProfesorXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new ProfesorXMLDAO(rutaDocumento,"profesores");
    }

    private ProfesorXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static ProfesorXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new ProfesorXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarProfesor(Profesor profesor) throws IOException {
        Element eProfesor = new Element("profesor");
        eProfesor.setAttribute("carnet", profesor.getCarnet());

        Element eNombre = new Element("nombre");
        eNombre.addContent(profesor.getNombre());
        eProfesor.addContent(eNombre);

        Element eApellidos = new Element("apellidos");
        eApellidos.addContent(profesor.getApellidos());
        eProfesor.addContent(eApellidos);

        Element eCurso = new Element("curso");
        eCurso.addContent(profesor.getCurso());
        eProfesor.addContent(eCurso);

        raiz.addContent(eProfesor);
        guardar();
    }
    public ArrayList<Profesor> getProfesores() throws DataConversionException {
        List eListaProfesores = raiz.getChildren();
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();

        for (Object obj : eListaProfesores) {
            Element eProfesor = (Element) obj;
            Profesor profesorActual = new Profesor();
            profesorActual.setCarnet(eProfesor.getChildText("carnet"));
            profesorActual.setNombre(eProfesor.getChildText("nombre"));
            profesorActual.setApellidos(eProfesor.getChildText("apellidos"));
            profesorActual.setCurso(eProfesor.getChildText("curso"));
            profesores.add(profesorActual);
        }
        return profesores;
    }
}
