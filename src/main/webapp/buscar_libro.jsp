<%@page import="java.util.List"%>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Libros</title>
    <style>
        body {
            background-color:#f5f5dc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Buscar Libros</h1>

    <form>
        <input type="text" name="titulo" placeholder="Ingrese el título, autor, tema o editorial">
        <button type="submit">Buscar</button>
    </form>

    <div id="resultadosBusqueda"></div>

    <table border="1">
        <thead>
        <tr>
            <th>ID Libro</th>
            <th>ISBN</th>
            <th>Título</th>
            <th>Editorial</th>
            <th>Tema</th>
            <th>Autores</th>
        </tr>
        </thead>

        <%
            List<Libro> librosFiltrados = new ArrayList<>();
            String titulo = request.getParameter("titulo");

            if (titulo == null || titulo.isEmpty()) {
                LibrosXMLDAO librosXmlDao = LibrosXMLDAO.abrirDocumento("libros.xml");
                librosFiltrados = librosXmlDao.getLibros();
            } else {
                LibrosXMLDAO librosXmlDao = LibrosXMLDAO.abrirDocumento("libros.xml");
                List<Libro> libros = librosXmlDao.getLibros();

                for (Libro libro : libros) {
                    List<Autor> autores = libro.getAutores();

                    if (libro.getTitulo().contains(titulo) ||
                            libro.getAutores().contains(titulo) ||
                            libro.getTematica().getNombreTematica().contains(titulo) ||
                            libro.getEditorial().getNombreEditorial().contains(titulo)) {
                        librosFiltrados.add(libro);
                    }

                    // Buscar por autor
                    for (Autor autor : autores) {
                        if (autor.getNombreAutor().contains(titulo)) {
                            librosFiltrados.add(libro);
                        }
                        if (autor.getApellidoAutor().contains(titulo)){
                            librosFiltrados.add(libro);
                        }
                    }
                }
            }

            request.setAttribute("libros", librosFiltrados);

            EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
            List<Editorial> editoriales = editorialesXMLDAO.getEditoriales();

            AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
            List<Autor> autores = autorXMLDAO.getAutores();

            TematicasXMLDAO tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
            List<Tematica> tematicas = tematicasXMLDAO.getTematicas();
        %>

        <tbody>
        <% for (Libro libroActual : librosFiltrados) { %>
        <tr>
            <td><%= libroActual.getLibroID() %></td>
            <td><%= libroActual.getISBN() %></td>
            <td><%= libroActual.getTitulo() %></td>
            <td><%= libroActual.getEditorial().getNombreEditorial() %></td>
            <td><%= libroActual.getTematica().getNombreTematica() %></td>
            <td><%= libroActual.getAutores() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>