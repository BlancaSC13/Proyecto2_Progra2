package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
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

public class EditorialesXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public EditorialesXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static EditorialesXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new EditorialesXMLDAO(rutaDocumento,"cursos");
    }

    private EditorialesXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static EditorialesXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new EditorialesXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarCurso(Curso curso) throws IOException {
        Element eCurso = new Element("curso");
        eCurso.setAttribute("id", String.valueOf(curso.getId()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(curso.getNombre());
        eCurso.addContent(eNombre);

        Element eCarrera = new Element("carrera");
        eCarrera.addContent(curso.getCarrera());
        eCurso.addContent(eCarrera);

        Element eSemestre = new Element("semestre");
        eSemestre.addContent(curso.getSemestre());
        eCurso.addContent(eSemestre);

        raiz.addContent(eCurso);
        guardar();
    }
    public ArrayList<Curso> getCursos() throws DataConversionException {
        List eListaCursos = raiz.getChildren();
        ArrayList<Curso> cursos = new ArrayList<Curso>();

        for (Object obj : eListaCursos) {
            Element eCurso = (Element) obj;
            Curso cursoActual = new Curso();
            cursoActual.setId(eCurso.getAttribute("id").getIntValue());
            cursoActual.setNombre(eCurso.getChildText("nombre"));
            cursoActual.setCarrera(eCurso.getChildText("carrera"));
            cursoActual.setSemestre(eCurso.getChildText("semestre"));
            cursos.add(cursoActual);
        }
        return cursos;
    }
}
