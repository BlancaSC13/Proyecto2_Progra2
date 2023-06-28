package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
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

public class AutoresXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public AutoresXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static AutoresXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new AutoresXMLDAO(rutaDocumento,"autores");
    }

    private AutoresXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static AutoresXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new AutoresXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarCurso(Autor autor) throws IOException {
        Element eAutor = new Element("autor");
        eAutor.setAttribute("id", String.valueOf(autor.getAutorID()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(autor.getNombreAutor());
        eAutor.addContent(eNombre);

        Element eApellido = new Element("apellido");
        eApellido.addContent(autor.getApellidoAutor());
        eAutor.addContent(eApellido);

        raiz.addContent(eAutor);
        guardar();
    }
    public ArrayList<Autor> getAutores() throws DataConversionException {
        List eListaAutores = raiz.getChildren();
        ArrayList<Autor> autores = new ArrayList<Autor>();

        for (Object obj : eListaAutores) {
            Element eCurso = (Element) obj;
            Autor autorActual = new Autor();
            autorActual.setAutorID(eCurso.getAttribute("id").getIntValue());
            autorActual.setNombreAutor(eCurso.getChildText("nombre"));
            autorActual.setApellidoAutor(eCurso.getChildText("carrera"));
            autores.add(autorActual);
        }
        return autores;
    }
}
