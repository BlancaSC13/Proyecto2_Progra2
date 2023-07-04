package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;


import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO;
import cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO;
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
import java.util.List;
import java.util.Optional;

@WebServlet("/ingresar")
public class IngresarLibroServlet extends HttpServlet {
    private List<Editorial> editoriales;
    private List<Tematica> tematicas;
    private List<Autor> autores;
    private Libro libroAInsertar;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TematicasXMLDAO tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
            //List<Tematica> tematicasXML;
            tematicasXMLDAO.getTematicas();
            EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
            editorialesXMLDAO.getEditoriales();
            AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
            autorXMLDAO.getAutores();

            req.setAttribute("tematicas", tematicas);
            req.getRequestDispatcher("/insertar_tematica.jsp").forward(req,resp);
            req.setAttribute("editoriales", editoriales);
            req.getRequestDispatcher("/insertar_editorial.jsp").forward(req,resp);
            req.setAttribute("autores", autores);
            req.getRequestDispatcher("/insertar_autores.jsp").forward(req,resp);
            /*req.setAttribute();
            req.getRequestDispatcher().forward(req,resp);*/

        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Libro libro = new Libro();
        libro.setLibroID(Integer.parseInt(req.getParameter("id")));
        libro.setTitulo(req.getParameter("titulo"));
      //  libro.setAutores(req.getParameter("autores"));
      /*  libro.setTematica(req.getParameter("tematicas"));
        libro.setEditorial(req.getParameter("editoriales"));*/ //tiene que recorrerse las listas


    }



}
