package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
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

public class AutorXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public AutorXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static AutorXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new AutorXMLDAO(rutaDocumento,"autores");
    }

    private AutorXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static AutorXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new AutorXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarAutores(Autor autor) throws IOException {
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
    public Autor buscar(int idAutor){
        List<Element> eAutores = raiz.getChildren();
        Autor autor = new Autor();
        for (Element eAutor: eAutores ) {
            if (Integer.parseInt( eAutor.getAttributeValue("id") ) == idAutor){
                autor.setAutorID(Integer.parseInt( eAutor.getAttributeValue("id") ));
                autor.setNombreAutor(eAutor.getChildText("nombre"));
                autor.setApellidoAutor(eAutor.getChildText("apellido"));
            }
        }
        return autor;
    }
    public Autor buscarNombre(String nombre){
        List<Element> eAutores = raiz.getChildren();
        Autor autor = new Autor();
        for (Element eAutor: eAutores ) {
            String nombreCompleto = eAutor.getChildText("nombre") + " " + eAutor.getChildText("apellido");
            if (nombreCompleto.equals(nombre)){
                autor.setAutorID(Integer.parseInt( eAutor.getAttributeValue("id") ));
                autor.setNombreAutor(eAutor.getChildText("nombre"));
                autor.setApellidoAutor(eAutor.getChildText("apellido"));
            }
        }
        return autor;
    }

    public ArrayList<Autor> getAutores() throws DataConversionException {
        List eListaAutores = raiz.getChildren();
        ArrayList<Autor> autores = new ArrayList<Autor>();

        for (Object obj : eListaAutores) {
            Element eAutor = (Element) obj;
            Autor autorActual = new Autor();
            autorActual.setAutorID(eAutor.getAttribute("id").getIntValue());
            autorActual.setNombreAutor(eAutor.getChildText("nombre"));
            autorActual.setApellidoAutor(eAutor.getChildText("apellido"));
            autores.add(autorActual);
        }
        return autores;
    }
}
