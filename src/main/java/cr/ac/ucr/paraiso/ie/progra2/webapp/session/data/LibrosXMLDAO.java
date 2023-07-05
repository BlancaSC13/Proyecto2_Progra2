package cr.ac.ucr.paraiso.ie.progra2.webapp.session.data;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibrosXMLDAO {
    private Document document;
    private Element raiz; //pequeños objetos dentro del documento
    private String rutaDocumento;
    private AutorXMLDAO autorXMLDAO;
    private TematicasXMLDAO tematicasXMLDAO;
    private EditorialesXMLDAO editorialesXMLDAO;


    public LibrosXMLDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static LibrosXMLDAO crearDocumento(String rutaDocumento) throws IOException {
        return new LibrosXMLDAO(rutaDocumento, "libros");
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
        Element eLibro = new Element("libro");
        eLibro.setAttribute("id", String.valueOf(libro.getLibroID()));

        Element eTitulo = new Element("titulo");
        eTitulo.addContent(libro.getTitulo());
        eLibro.addContent(eTitulo);

        Element eISBN = new Element("isbn");
        eISBN.addContent(String.valueOf(libro.getISBN()));
        eLibro.addContent(eISBN);

        Element eAutores = new Element("autores");
        for (Autor autor : libro.getAutores()) {
            Element eAutor = new Element("idAutor");
            eAutor.addContent(String.valueOf(autor.getAutorID()));
            eAutores.addContent(eAutor);
        }
        eLibro.addContent(eAutores);

        Element eEditorial = new Element("editorial");
        eEditorial.addContent(String.valueOf((libro.getEditorial().getEditorialID())));
        eLibro.addContent(eEditorial);

        Element eTematica = new Element("tematica");
        eTematica.addContent(libro.getTematica().getNombreTematica());
        eLibro.addContent(eTematica);

        raiz.addContent(eLibro);
        guardar();
    }

    public void eliminarLibro(int libroID) throws IOException, DataConversionException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        Iterator<Element> iterator = eListaLibros.iterator();

        int i = 0;
        for (Element eLibro : eListaLibros) {

            if (eLibro.getAttribute("id").getIntValue() == libroID) {
                eListaLibros.remove(i);
                // iterator.remove();
                break;
            }
            i++;
        }

        guardar();
    }

    public ArrayList<Libro> buscarTitulo(String titulo) throws JDOMException, IOException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        inicializarXML();
        List<Autor> autoresEncontrados = new ArrayList<Autor>();
        for (Element eLibro : eListaLibros) {
            String tituloActual = eLibro.getChildText("titulo");
            if (tituloActual.toLowerCase().equals(titulo.toLowerCase())) {
                Libro libroEncontrado = new Libro();
                libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
                libroEncontrado.setTitulo(tituloActual);
                List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
                for (Element eIdAutor : eAutores) {
                    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                    Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutor.getText()));
                    libroEncontrado.getAutores().add(autor);
                }
                libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
                libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
                librosEncontrados.add(libroEncontrado);
            }

        }
        return librosEncontrados;
    }

    public ArrayList<Libro> buscarAutor(int idAutor) throws JDOMException, IOException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        inicializarXML();
        List<Autor> autoresEncontrados = new ArrayList<Autor>();
        for (Element eLibro : eListaLibros) {
            List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
            for (Element eIdAutor : eAutores) {
                if (Integer.parseInt(eIdAutor.getText()) == idAutor) {
                    Libro libroEncontrado = new Libro();
                    libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                    libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
                    libroEncontrado.setTitulo(eLibro.getChildText("titulo"));
                    List<Element> eAutoresActuales = eLibro.getChild("autores").getChildren("idAutor");
                    for (Element eIdAutorActual : eAutoresActuales) {
                        AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                        Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutorActual.getText()));
                        libroEncontrado.getAutores().add(autor);
                    }
                    libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
                    libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
                    librosEncontrados.add(libroEncontrado);
                }

            }

        }
        return librosEncontrados;
    }

    public ArrayList<Libro> buscarTematica(String tematicaBuscada) throws JDOMException, IOException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        inicializarXML();
        List<Autor> autoresEncontrados = new ArrayList<Autor>();
        for (Element eLibro : eListaLibros) {
            String tematicaActual = eLibro.getChildText("tematica");
            if (tematicaActual.toLowerCase().equals(tematicaBuscada.toLowerCase())) {
                Libro libroEncontrado = new Libro();
                libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
                libroEncontrado.setTitulo(eLibro.getChildText("titulo"));
                List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
                for (Element eIdAutor : eAutores) {
                    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                    Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutor.getText()));
                    libroEncontrado.getAutores().add(autor);
                }
                libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
                libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
                librosEncontrados.add(libroEncontrado);
            }

        }
        return librosEncontrados;
    }

    public ArrayList<Libro> buscarEditorial(String editorialBuscada) throws JDOMException, IOException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        inicializarXML();
        for (Element eLibro : eListaLibros) {
            int editorialActual = Integer.parseInt(eLibro.getChildText("editorial"));
            Editorial editorial = editorialesXMLDAO.buscar(editorialActual);
            if (editorial.getNombreEditorial().equals(editorialBuscada)) {
                Libro libroEncontrado = new Libro();
                libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
                libroEncontrado.setTitulo(eLibro.getChildText("titulo"));
                List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
                for (Element eIdAutor : eAutores) {
                    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                    Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutor.getText()));
                    libroEncontrado.getAutores().add(autor);
                }
                libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
                libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
                librosEncontrados.add(libroEncontrado);
            }

        }
        return librosEncontrados;
    }

    public ArrayList<Libro> getLibros() throws JDOMException, IOException {
        List<Element> eLibros = raiz.getChildren();
        ArrayList<Libro> libros = new ArrayList<Libro>();
        inicializarXML();
        for (Element eLibro : eLibros) {
            Libro libroEncontrado = new Libro();
            libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
            libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
            libroEncontrado.setTitulo(eLibro.getChildText("titulo"));
            List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
            for (Element eIdAutor : eAutores) {
                AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutor.getText()));
                libroEncontrado.getAutores().add(autor);
            }
            /*  libroActual.setAutor(eLibro.getChildText("autor"));*/
            libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
            libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
            libros.add(libroEncontrado);
        }
        return libros;
    }

    public Libro getLibros(int idLibro) throws JDOMException, IOException {
        List<Element> eLibros = raiz.getChildren();
        inicializarXML();
        Libro libroEncontrado = new Libro();
        for (Element eLibro : eLibros) {
            if (eLibro.getAttribute("id").getIntValue() == idLibro) {
                libroEncontrado.setLibroID(eLibro.getAttribute("id").getIntValue());
                libroEncontrado.setISBN(Integer.parseInt(eLibro.getChildText("isbn")));
                libroEncontrado.setTitulo(eLibro.getChildText("titulo"));
                List<Element> eAutores = eLibro.getChild("autores").getChildren("idAutor");
                for (Element eIdAutor : eAutores) {
                    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                    Autor autor = autorXMLDAO.buscar(Integer.parseInt(eIdAutor.getText()));
                    libroEncontrado.getAutores().add(autor);
                }
                libroEncontrado.setEditorial(editorialesXMLDAO.buscar(Integer.parseInt(eLibro.getChildText("editorial"))));
                libroEncontrado.setTematica(tematicasXMLDAO.buscar(eLibro.getChildText("tematica")));
            }
        }
        return libroEncontrado;
    }

    private void inicializarXML() throws IOException, JDOMException {
        autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
        tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
    }

    public boolean buscar(int idLibro) throws JDOMException, IOException {
        List<Element> eListaLibros = raiz.getChildren("libro");
        inicializarXML();
        for (Element eLibro : eListaLibros) {
            int idBuscando = eLibro.getAttribute("id").getIntValue();
            if (idBuscando == idLibro) {
                return true;
            }
        }
        return false;
    }

    public Libro modificar(Libro libro) throws IOException, JDOMException {
        List<Element> eLibros = raiz.getChildren();
        ArrayList<Element> libros = new ArrayList<Element>();
        inicializarXML();
        for (Element eLibro : eLibros) {
            if (eLibro.getAttribute("id").getIntValue() == libro.getLibroID()) {
                Element eLibroModificado = new Element("libro");
                eLibroModificado.setAttribute("id", String.valueOf(libro.getLibroID()));

                Element eTitulo = new Element("titulo");
                eTitulo.addContent(libro.getTitulo());
                eLibroModificado.addContent(eTitulo);

                Element eISBN = new Element("isbn");
                eISBN.addContent(String.valueOf(libro.getISBN()));
                eLibroModificado.addContent(eISBN);

                Element eAutores = new Element("autores");
                for (Autor autor : libro.getAutores()) {
                    Element eAutor = new Element("idAutor");
                    eAutor.addContent(String.valueOf(autor.getAutorID()));
                    eAutores.addContent(eAutor);
                }
                eLibroModificado.addContent(eAutores);

                Element eEditorial = new Element("editorial");
                eEditorial.addContent(String.valueOf((libro.getEditorial().getEditorialID())));
                eLibroModificado.addContent(eEditorial);

                Element eTematica = new Element("tematica");
                eTematica.addContent(libro.getTematica().getNombreTematica());
                eLibroModificado.addContent(eTematica);
                libros.add(eLibroModificado);
            } else {
                libros.add(eLibro);
            }
        }
        this.raiz.getContent().clear();
        for (Element libroLista : libros) {
            raiz.addContent(libroLista);
        }
        guardar();
        return libro;
    }

    public ArrayList<Integer> geID() throws JDOMException, IOException {
        List<Element> eLibros = raiz.getChildren();
        ArrayList<Integer> identificadores = new ArrayList<Integer>();
        inicializarXML();
        for (Element eLibro : eLibros) {
            identificadores.add(eLibro.getAttribute("id").getIntValue());
        }
        return identificadores;
    }

}

