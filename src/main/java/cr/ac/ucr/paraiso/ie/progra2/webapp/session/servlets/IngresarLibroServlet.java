package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;


import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.*;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CursoService;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.CursoServiceImp;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.SessionService;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.SessionServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/ingresar")
public class IngresarLibroServlet extends HttpServlet {
    public List<Editorial> listaEditoriales;
    public List<Tematica> listaTematicas;
    public List<Autor> listaAutores;
    public List<Libro> listaLibros;
    public Libro libroInsertar;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //resp.setContentType("text/html");

       /*     listaTematicas = TematicasXMLDAO.abrirDocumento("tematicas.xml").getTematicas();
            req.setAttribute("tematicas", listaTematicas);
            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);

            listaEditoriales = EditorialesXMLDAO.abrirDocumento("editoriales.xml").getEditoriales();
            req.setAttribute("editoriales", listaEditoriales);
            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);

            listaAutores = AutorXMLDAO.abrirDocumento("autores.xml").getAutores();
            req.setAttribute("autores", listaAutores);
            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);
*/
            listaTematicas = TematicasXMLDAO.abrirDocumento("tematicas.xml").getTematicas();
            req.setAttribute("tematicas", listaTematicas);

            listaEditoriales = EditorialesXMLDAO.abrirDocumento("editoriales.xml").getEditoriales();
            req.setAttribute("editorial", listaEditoriales);

            listaAutores = AutorXMLDAO.abrirDocumento("autores.xml").getAutores();
            req.setAttribute("autores", listaAutores);

            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);

        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html");

        String identificacion = req.getParameter("identificacion");
        String isbn = req.getParameter("isbn");
        String titulo = req.getParameter("titulo");
        String tematica = req.getParameter("tematicas");
        String editorialID = req.getParameter("editorial");
        String autorID = req.getParameter("autores");

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("Autores: " + autorID);

            List<String> check = new ArrayList<>();

            if (identificacion == null || identificacion.equals("")) check.add("El id del libro es requerido");
            if (titulo == null || titulo.equals("")) check.add("El título del libro es requerido");
            if (isbn == null || isbn.equals("")) check.add("El ISBN del libro es requerido");


            if (check.isEmpty()) {
                LibrosXMLDAO librosXMLDAO;
                try {
                    librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
                } catch (JDOMException e) {
                    throw new RuntimeException(e);
                }

                if (!librosXMLDAO.buscar(Integer.parseInt(identificacion))) {
                    Libro libro = new Libro();
                    libro.setLibroID(Integer.parseInt(identificacion));
                    libro.setISBN(Integer.parseInt(isbn));
                    libro.setTitulo(titulo);

                    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
                    List<Autor> autores = autorXMLDAO.getAutores();
                    List<Autor> autoresSeleccionados = new ArrayList<>();

                    int autorIDInt = Integer.parseInt(autorID);
                    for (Autor autor : autores) {
                        out.println("Autor ID: " + autor.getAutorID());
                        if (autorIDInt == autor.getAutorID()) {
                            autoresSeleccionados.add(autor);
                        }
                    }

                    if (!autoresSeleccionados.isEmpty()) {
                        libro.setAutores(autoresSeleccionados);
                    }


                    EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
                    List<Editorial> editoriales = editorialesXMLDAO.getEditoriales();
                    Editorial editorialSeleccionada = null;

                    for (Editorial editorial : editoriales) {
                        if (editorial.getEditorialID() == Integer.parseInt(editorialID)) {
                            editorialSeleccionada = editorial;
                            break;
                        }
                    }

                    if (editorialSeleccionada != null) {
                        libro.setEditorial(editorialSeleccionada);
                    }


                    Tematica tematicas = new Tematica();
                    tematicas.setNombreTematica(tematica);
                    libro.setTematica(tematicas);

                    librosXMLDAO.insertarLibro(libro);
                    req.setAttribute("insertado", true);
                } else {
                    req.setAttribute("insertado", false);
                }
                getServletContext().getRequestDispatcher("/servletEjemplo").forward(req, resp);
            }else{
                req.setAttribute("check", check);
                req.setAttribute("libros", listaLibros);
                getServletContext().getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);
            }


        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

}



