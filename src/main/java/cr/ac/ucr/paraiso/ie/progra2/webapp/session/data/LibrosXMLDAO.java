package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LibrosXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;

    public LibrosXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static LibrosXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new LibrosXMLDAO(rutaDocumento,"libros");
    }

    private LibrosXMLDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static LibrosXMLDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new LibrosXMLDAO(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));
        //Extra para revisión
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarLibro(Libro libro) throws IOException {
        Element eLibro = new Element("libros");
        eLibro.setAttribute("id", String.valueOf(libro.getLibroID()));

        Element eTitulo = new Element("titulo");
        eTitulo.addContent(libro.getTitulo());
        eLibro.addContent(eTitulo);

        Element eAutor = new Element("autor");
        eAutor.addContent((Collection<? extends Content>) libro.getAutor());
        eLibro.addContent(eAutor);

        Element eEditorial = new Element("editorial");
        eEditorial.addContent((Collection<? extends Content>) libro.getEditorial());
        eLibro.addContent(eEditorial);

        Element eTematica = new Element("tematica");
        eTematica.addContent((Collection<? extends Content>) libro.getTematica());
        eLibro.addContent(eTematica);

        raiz.addContent(eLibro);
        guardar();
    }

    public void eliminarLibro(int libroID) throws IOException, DataConversionException {
        List<Element> eListaLibros = raiz.getChildren("libros");
        Iterator<Element> iterator = eListaLibros.iterator();

        while (iterator.hasNext()) {
            Element eLibro = iterator.next();
            if (eLibro.getAttribute("id").getIntValue() == libroID) {
                iterator.remove();
                break;
            }
        }

        guardar();
    }

    public ArrayList<Libro> buscarLibro(String consulta) throws DataConversionException {
        List<Element> eListaLibros = raiz.getChildren("libros");
        ArrayList<Libro> librosEncontrados = new ArrayList<>();

        for (Element eLibro : eListaLibros) {
            String titulo = eLibro.getChildText("titulo");
            if (titulo != null && titulo.toLowerCase().contains(consulta.toLowerCase())) {
                Libro libroEncontrado = new Libro();
                libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                libroEncontrado.setTitulo(titulo);
                /*
                libroEncontrado.setAutor(eLibro.getChildText("autor"));
                libroEncontrado.setEditorial(eLibro.getChildText("editorial"));
                libroEncontrado.setTematica(eLibro.getChildText("tematica"));
                */
                librosEncontrados.add(libroEncontrado);
            }
        }

        return librosEncontrados;
    }
    public ArrayList<Libro> getLibros() throws DataConversionException {
        List eListaLibros = raiz.getChildren();
        ArrayList<Libro> libros = new ArrayList<Libro>();

        for (Object obj : eListaLibros) {
            Element eLibro = (Element) obj;
            Libro libroActual = new Libro();
            libroActual.setLibroID(eLibro.getAttribute("id").getIntValue());
            libroActual.setTitulo(eLibro.getChildText("titulo"));
           /* libroActual.setAutor(eLibro.getChildText("autor"));
            libroActual.setEditorial(eLibro.getChildText("editorial"));
            libroActual.setTematica(eLibro.getChildText("tematica"));*/
            libros.add(libroActual);
        }
        return libros;
    }
}
