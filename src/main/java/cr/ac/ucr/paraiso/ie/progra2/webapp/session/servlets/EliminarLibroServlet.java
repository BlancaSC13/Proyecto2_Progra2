package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;


import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;

@WebServlet("/eliminar")
public class EliminarLibroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibrosXMLDAO librosXMLDAO;
        try {
            librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
            if (librosXMLDAO.buscar(Integer.parseInt(req.getParameter("identificacion"))))
            {
                librosXMLDAO.eliminarLibro(Integer.parseInt(req.getParameter("identificacion")));
                getServletContext().getRequestDispatcher("/confirmacion_eliminar.html").forward(req, resp);
            }else{
                getServletContext().getRequestDispatcher("/negar_eliminar.html").forward(req, resp);
            }

        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }



}
