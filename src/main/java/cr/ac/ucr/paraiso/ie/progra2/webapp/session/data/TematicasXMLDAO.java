package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;


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

public class TematicasXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public TematicasXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static TematicasXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new TematicasXMLDAO(rutaDocumento,"tematicas");
    }

    private TematicasXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static TematicasXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new TematicasXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarTematicas(Tematica tematica) throws IOException {
        Element eTematica = new Element("tematicas");
        eTematica.setAttribute("id", String.valueOf(tematica.getTematicaID()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(tematica.getNombreTematica());
        eTematica.addContent(eNombre);

        raiz.addContent(eTematica);
        guardar();
    }
    public ArrayList<Tematica> getTematicas() throws DataConversionException {
        List eListaTematicas = raiz.getChildren();
        ArrayList<Tematica> tematicas = new ArrayList<Tematica>();

        for (Object obj : eListaTematicas) {
            Element eTematica = (Element) obj;
            Tematica tematicaActual = new Tematica();
            tematicaActual.setTematicaID(eTematica.getAttribute("id").getIntValue());
            tematicaActual.setNombreTematica(eTematica.getChildText("nombre"));
            tematicas.add(tematicaActual);
        }
        return tematicas;
    }
    public Tematica buscar (String nombre) throws DataConversionException {
        List eListaTematicas = raiz.getChildren();
        Tematica tematicaEncontrada = new Tematica();
        for (Object obj:eListaTematicas) {
            Element eTematica = (Element) obj;
            if (eTematica.getChildText("nombre").equals(nombre)){
                tematicaEncontrada.setTematicaID(eTematica.getAttribute("id").getIntValue());
                tematicaEncontrada.setNombreTematica(nombre);
            }
        }
        return tematicaEncontrada;
    }
}
