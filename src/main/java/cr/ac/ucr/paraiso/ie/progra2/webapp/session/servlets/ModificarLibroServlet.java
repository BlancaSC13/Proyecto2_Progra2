package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;


import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modificar")
public class ModificarLibroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibrosXMLDAO librosXMLDAO;
        TematicasXMLDAO tematicasXMLDAO;
        EditorialesXMLDAO editorialesXMLDAO;
        resp.setContentType("text/html;charset=UTF-8");
        try {
            librosXMLDAO=LibrosXMLDAO.abrirDocumento("libros.xml");
            if (librosXMLDAO.buscar(Integer.parseInt(req.getParameter("identificacion")))) {
                librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
                tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
                editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
                int id = Integer.parseInt(req.getParameter("identificacion"));
                Libro libro = librosXMLDAO.getLibros(id);
                libro.setTitulo(req.getParameter("titulo"));
                libro.setTematica(tematicasXMLDAO.buscar(req.getParameter("tematica")));
                libro.setISBN(Integer.parseInt(req.getParameter("isbn")));
                libro.setEditorial(editorialesXMLDAO.buscar(req.getParameter("editorial")));
                librosXMLDAO.modificar(libro);
                req.getRequestDispatcher("/modificar_aceptado.html").forward(req, resp);
            }else{
                req.getRequestDispatcher("/modificar_denegado.html").forward(req, resp);
            }

        } catch (JDOMException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
