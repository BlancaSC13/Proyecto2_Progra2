<%@page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ingresar un libro</title>
    <% TematicasXMLDAO tematicasXmlDao = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        List<Tematica> tematicass = tematicasXmlDao.getTematicas(); %>

    <%
        EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
        List<Editorial> editoriales = editorialesXMLDAO.getEditoriales(); %>

    <%
        AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
        List<Autor> autoress = autorXMLDAO.getAutores(); %>


    <style>
        body {
            background-color: #f5f5dc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
        }

        #form-container {
            display: flex;
            flex-direction: column;
            margin-right: 50px;
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
<div id="form-container">
    <%--@declare id="tematicas"--%><h1>Ingresar un libro</h1>
    <label for="id"><span>Id:</span><input type="text" id="id"/></label>
    <label for="isbn"><span>ISBN:</span><input type="text" id="isbn"/></label>
    <label for="titulo"><span>Título:</span><input type="text" id="titulo"/></label>

        <% List<Editorial> editorialess = (List<Editorial>) request.getAttribute("editoriales"); %>


<%System.out.println("2" +editoriales);%>
    <label for="editorial"><span>Editorial:</span>
        <select id="editorial">
            <%System.out.println("2" +editoriales);
                for (Editorial editorial : editoriales) {%>
            <option value="<%= editorial.getEditorialID() %>"><%= editorial.getNombreEditorial() %>
            </option>
            <% } %>
        </select>
    </label>

    <label for="tematicas">Temática:
    <select style="width:300px" border="1px" name="tematicas">
        <% for (Tematica tematica : tematicass) {%>
        <option value="<%= tematica.getNombreTematica() %>"><%= tematica.getNombreTematica() %>
        </option>
        <% } %>
    </select>
    </label>

    <label for="autores"><span>Autor/es:</span>

        <select id="autores">
            <% for (Autor autor : autoress) {%>
            <option value="<%= autor.getAutorID() %>"><%= autor.getNombreAutor() %>
            </option>
            <% } %>
        </select>
        <button id="btn-agregar-autor">Agregar Autor</button>
        <input type="submit" value="Agregar Autor" class="btn btn-success">
    </label>
    <label for="estado"><span>Estado:</span>
        <select id="estado">
            <option value="estado1">Estado 1</option>
            <option value="estado2">Estado 2</option>
            <option value="estado3">Estado 3</option>
        </select>
    </label>
    <input type="submit" value="Añadir Libro" class="btn btn-success">
</div>

<table id="authors-table">
    <thead>
    <tr>
        <th>idAutor</th>
        <th>Nombre</th>
        <th>Apellido</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>Nombre Autor 1</td>
        <td>Apellido Autor 1</td>
    </tr>
    <tr>
        <td>2</td>
        <td>Nombre Autor 2</td>
        <td>Apellido Autor 2</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Nombre Autor 3</td>
        <td>Apellido Autor 3</td>
    </tr>
    </tbody>
</table>
</body>
</html>
