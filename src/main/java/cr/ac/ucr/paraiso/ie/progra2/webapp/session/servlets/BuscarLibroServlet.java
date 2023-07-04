package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;


import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.LibroService;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.service.LibroServiceImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buscar")
public class BuscarLibroServlet extends HttpServlet {
    public List<Libro> listaLibros;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibroService libroService = new LibroServiceImp();
      //  List<Libro> libros = null;

        try {

            listaLibros = LibrosXMLDAO.abrirDocumento("libros.xml").getLibros();
            req.setAttribute("tematicas", listaLibros);
            req.getRequestDispatcher("/buscar_libros.jsp").forward(req, resp);
            req.setAttribute("libros", listaLibros);
            req.getRequestDispatcher("/buscar_libro.jsp").forward(req,resp);



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
        RequestDispatcher dispatcher = req.getRequestDispatcher("resultadoBusqueda.jsp");
        dispatcher.forward(req, resp);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }


        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        }


    }
