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
import java.util.Iterator;
import java.util.List;

public class CursoXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public CursoXMLDAO(String rutaDocumento,String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static CursoXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new CursoXMLDAO(rutaDocumento,"cursos");
    }

    private CursoXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static CursoXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new CursoXMLDAO(rutaDocumento);
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
    public void eliminarCurso(int cursoID) throws IOException, DataConversionException {
        List<Element> eListaCursos = raiz.getChildren("cursos");
        Iterator<Element> iterator = eListaCursos.iterator();

        while (iterator.hasNext()) {
            Element eCurso = iterator.next();
            if (eCurso.getAttribute("id").getIntValue() == cursoID) {
                iterator.remove();
                break;
            }
        }

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
