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

            listaTematicas = TematicasXMLDAO.abrirDocumento("tematicas.xml").getTematicas();
            req.setAttribute("tematicas", listaTematicas);
            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);

            listaEditoriales = EditorialesXMLDAO.abrirDocumento("editoriales.xml").getEditoriales();
            req.setAttribute("editoriales", listaEditoriales);
            req.getRequestDispatcher("/insertar_libro.jsp").forward(req, resp);

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
        int identificacion = Integer.parseInt(req.getParameter("id"));
        int isbn = Integer.parseInt(req.getParameter("isbn"));
        String titulo = req.getParameter("titulo");
        String tematica = req.getParameter("tematicas");
        String editorial = req.getParameter("editorial");
        String autores = req.getParameter("autores");

        List<String> check = new ArrayList<>();

        if (titulo == null || titulo.equals("")) check.add("El título del libro es requerido");
        if (tematica == null || tematica.equals("")) check.add("La tematica es es requerida");
        if (autores == null || autores.equals("")) check.add("Los autores son requeridos");

        if (check.isEmpty()) {
            LibrosXMLDAO librosXMLDAO = null;
            try {
                librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }

            try {
                if (!librosXMLDAO.buscar(identificacion)) {
                    Libro libro = new Libro();
                    libro.setLibroID(identificacion);
                    libro.setISBN(isbn);
                    libro.setTitulo(titulo);

                    Autor autors = new Autor();
                    autors.setNombreAutor(autores);
                    libro.setAutores((List<Autor>) autors);

                    Editorial editorial1 = new Editorial();
                    editorial1.setNombreEditorial(editorial);

                    Tematica tematica1 = new Tematica();
                    tematica1.setNombreTematica(tematica);

                    librosXMLDAO.insertarLibro(libro);
                    req.setAttribute("insertado", true);
                } else {
                    req.setAttribute("insertado", false);
                }
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }

            getServletContext().getRequestDispatcher("/servletEjemplo").forward(req, resp);

        } else {
            req.setAttribute("check", check);
            req.setAttribute("libros", listaLibros);
            getServletContext().getRequestDispatcher("/insertar_libros.jsp").forward(req, resp);
        }


    }


}
