<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@ page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Libro</title>
</head>
<body>
<div class="container">
    <form action="/Proyecto2_Progra/eliminar" method="get">
        <h2>Modificar un libro</h2>
            <% LibrosXMLDAO librosXMLDAO;
            try {
                librosXMLDAO = LibrosXMLDAO.abrirDocumento("libros.xml");
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
            List<Integer> identificadores = librosXMLDAO.geID();
        %>
        <div class="cont_forms">
            <label for="identificacion"><span>Id:</span>
                <input type="text" id="identificacion" name="identificacion"required></label>
            <button type="submit">Eliminar</button>
        </div>
        <br><br>
</form>
</div>
<div>
    <table>
        <thead>
        <tr>
            <th>ID Libro</th>
            <th>ISBN</th>
            <th>Título</th>
            <th>Editorial</th>
            <th>Tematica</th>
            <th>Autores</th>
        </tr>
        </thead>
        <% LibrosXMLDAO librosXmlDao;
            List<Libro> libros;
            try {
                librosXmlDao = LibrosXMLDAO.abrirDocumento("libros.xml");
                libros = librosXmlDao.getLibros();
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
        %>

        <% EditorialesXMLDAO editorialesXMLDAO2 = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
            List<Editorial> editoriales = editorialesXMLDAO2.getEditoriales();%>

        <% AutorXMLDAO autorXMLDAO;
            try {
                autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
            List<Autor> autores = autorXMLDAO.getAutores();%>

        <% TematicasXMLDAO tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
            List<Tematica> tematicas = tematicasXMLDAO.getTematicas();%>

        <tbody>

        <%for (Libro libroActual : libros) { %>
        <tr>
            <td><%= libroActual.getLibroID() %>
            </td>
            <td><%= libroActual.getISBN() %>
            </td>
            <td><%= libroActual.getTitulo() %>
            </td>
            <td><%= libroActual.getEditorial().getNombreEditorial() %>
            </td>
            <td><%= libroActual.getTematica().getNombreTematica() %>
            </td>
            <td><%= libroActual.getAutores() %>
            </td>
        </tr>
        <% }%>
        </tbody>
    </table>
</div>
</body>
</html>