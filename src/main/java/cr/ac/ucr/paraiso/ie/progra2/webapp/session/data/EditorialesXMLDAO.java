package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Curso;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
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
        return new EditorialesXMLDAO(rutaDocumento,"editoriales");
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

    public void insertarEditoriales(Editorial editorial) throws IOException {
        Element eEditorial = new Element("editoriales");
        eEditorial.setAttribute("id", String.valueOf(editorial.getEditorialID()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(editorial.getNombreEditorial());
        eEditorial.addContent(eNombre);

        raiz.addContent(eEditorial);
        guardar();
    }
    public ArrayList<Editorial> getEditoriales() throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();
        ArrayList<Editorial> editoriales = new ArrayList<Editorial>();

        for (Object obj : eListaEditoriales) {
            Element eEditorial = (Element) obj;
            Editorial editorialActual = new Editorial();
            editorialActual.setEditorialID(eEditorial.getAttribute("id").getIntValue());
            editorialActual.setNombreEditorial(eEditorial.getChildText("nombre"));
            editoriales.add(editorialActual);
        }
        return editoriales;
    }
    public Editorial buscar (int idEditorial) throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();
        Editorial editorialEncontrada = new Editorial();
        for (Object obj:eListaEditoriales) {
            Element eEditorial = (Element) obj;
            if (eEditorial.getAttribute("id").getIntValue()==idEditorial){
               editorialEncontrada.setEditorialID(idEditorial);
               editorialEncontrada.setNombreEditorial(eEditorial.getChildText("nombre"));
            }
        }
        return editorialEncontrada;
    }
    public Editorial buscar (String nombreEditorial) throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();
        Editorial editorialEncontrada = new Editorial();
        for (Object obj:eListaEditoriales) {
            Element eEditorial = (Element) obj;
            if (eEditorial.getChildText("nombre").equals(nombreEditorial)){
                editorialEncontrada.setEditorialID(eEditorial.getAttribute("id").getIntValue());
                editorialEncontrada.setNombreEditorial(nombreEditorial);
            }
        }
        return editorialEncontrada;
    }
}
