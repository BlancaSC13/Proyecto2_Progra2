package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buscar")
public class BuscarLibroServlet extends HttpServlet {
    private List<Libro> listaLibros;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            listaLibros = LibrosXMLDAO.abrirDocumento("libros.xml").getLibros();
            req.setAttribute("libros", listaLibros);
            req.getRequestDispatcher("/buscar_libro.jsp").forward(req, resp);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String titulo = req.getParameter("titulo");

            LibrosXMLDAO librosXmlDao = LibrosXMLDAO.abrirDocumento("libros.xml");
            List<Libro> libros = librosXmlDao.getLibros();
            List<Libro> librosFiltrados = new ArrayList<>();

            for (Libro libro : libros) {
                if (libro.getTitulo().contains(titulo)) {
                    librosFiltrados.add(libro);
                }
            }

            req.setAttribute("libros", librosFiltrados);

            // Puedes redirigir a una página JSP para mostrar los resultados
            RequestDispatcher dispatcher = req.getRequestDispatcher("/buscar_libro.jsp");
            dispatcher.forward(req, resp);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }
}
