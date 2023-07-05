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
    <link rel="stylesheet" type="text/css" href="estilox.css/fondo.css">
    <style>
        body {
            background-color: #f5f5dc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
        }

        #form-container label {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        #form-container label span {
            margin-right: 10px;
        }

        #form-container input[type="text"],
        #form-container select {
            width: 200px;
        }

        #form-container #btn-agregar-autor {
            margin-top: 10px;
        }

        #authors-table th,
        #authors-table td {
            border: 1px solid black;
            padding: 5px;
        }
        button {
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 16px;
            border: beige;
            border-radius: 5px;
            background-color: #f5f5dc;
            color: black;
            cursor: pointer;
        }

        button:hover {
            background-color: #e6e6cc;
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
                <input type="text" id="identificacion" name="identificacion"required></label>
        </div>
        <br><br>

        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" required><br><br>
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br><br>

        <label for="editorial">Editorial:
            <select id="editorial" name="editorial" required>
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

        <label for="tematica">Temática:
            <select id="tematica" name="tematica" required>
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
        <button onclick="location.href='/Proyecto2_Progra'">Volver</button>

    </form>
</div>
<table border="1" style="background: white">
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
