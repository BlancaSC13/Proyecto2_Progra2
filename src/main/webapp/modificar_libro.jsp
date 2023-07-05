<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar un libro</title>
    <style>
        .container {
            background-color: #f5f5dc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 75vh;
            font-family: Arial, sans-serif;
        }

        form {
            width: 300px;
        }

        #authors-table {
            margin-top: 20px;
            border-collapse: collapse;
        }

        #authors-table th,
        #authors-table td {
            border: 1px solid black;
            padding: 5px;
        }

    </style>
</head>
<body>
<div class="container">
    <form action="/Proyecto2_Progra/modificar" method="get">
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
                <input type="text" id="identificacion" name="identificacion"/></label>
        </div>
        <br><br>

        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn"><br><br>
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo"><br><br>

        <label for="editorial">Editorial:
            <select id="editorial" name="editorial">
                <% EditorialesXMLDAO editorialesXMLDAO;
                    List<Editorial> editorials;
                    try {
                        editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
                        editorials = editorialesXMLDAO.getEditoriales();
                    } catch (JDOMException e) {
                        throw new RuntimeException(e);
                    }

                    for (Editorial editorial : editorials) {
                %>
                <option value="<%=editorial.getNombreEditorial()%>"><%=editorial.getNombreEditorial()%>
                </option>
                <%}%>
            </select>
            <br><br>
        </label>

        <label for="tematica">Editorial:
            <select id="tematica" name="tematica">
                <% TematicasXMLDAO tematicasXMLDAO2;
                    List<Tematica> tematicas2;
                    try {
                        tematicasXMLDAO2 = TematicasXMLDAO.abrirDocumento("tematicas.xml");
                        tematicas2 = tematicasXMLDAO2.getTematicas();
                    } catch (JDOMException e) {
                        throw new RuntimeException(e);
                    }

                    for (Tematica tematicaActual : tematicas2) {
                %>
                <option value="<%=tematicaActual.getNombreTematica()%>"><%=tematicaActual.getNombreTematica()%>
                </option>
                <%}%>
            </select>
            <br><br>
        </label>
        <button type="submit">Modificar Libro</button>
    </form>
</div>
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
</body>
</html>
