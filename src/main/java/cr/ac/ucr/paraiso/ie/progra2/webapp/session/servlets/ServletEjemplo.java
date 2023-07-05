package cr.ac.ucr.paraiso.ie.progra2.webapp.session.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author alvam
 */
@WebServlet("/servletEjemplo")
public class ServletEjemplo extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        boolean insertado = (boolean) req.getAttribute("insertado");
        String id = req.getParameter("idDelete");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Borrar</title>");
        out.println("    </head>");
        out.println("    <body>");
        if (insertado){
            out.println("        <h2 style=\"text-align:center:\">Libro insertado correctamente</h2>");
            out.println("<a href=\"./index.html\">Home</>");
        }else {
            out.println("        <h2 style=\"text-align:center:\">El id ingresado ya se encuentra en uso, por favor digite uno distinto</h2>");
            out.println("<a href=\"./ingresar\">Regresar</>");
        }
        out.println("    </body>");
        out.println("</html>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
