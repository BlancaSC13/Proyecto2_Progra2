<%@page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.LibrosXMLDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<String> errores = (List<String>) request.getAttribute("check");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ingresar un libro</title>
    <link rel="stylesheet" type="text/css" href="estilox.css/fondo.css">
    <style>
        body {
            background-color: #f5f5dc;
            background-repeat: no-repeat;
            background-size: cover;
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
<% TematicasXMLDAO tematicasXmlDao = TematicasXMLDAO.abrirDocumento("tematicas.xml");
    List<Tematica> tematicas = tematicasXmlDao.getTematicas(); %>

<%
    EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
    List<Editorial> editoriales = editorialesXMLDAO.getEditoriales(); %>

<%
    AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
    List<Autor> autores = autorXMLDAO.getAutores(); %>

<div id="container">
    <form action="/Proyecto2_Progra/ingresar" method="post">


        <h1>Ingresar un libro</h1>

        <div class="cont_forms">
            <%
                if (errores != null && errores.size() > 0) {
            %>
            <ul class="alert alert-danger">
                <% for (String error : errores) {%>
                <li><%=error%>
                </li>
                <%}%>
            </ul>
            <%}%>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="identificacion"><span>Id:</span>
                <input type="text" id="identificacion" name="identificacion"/></label>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="isbn"><span>ISBN:</span>
                <input type="text" placeholder="3654" name="isbn" id="isbn"/></label>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="titulo"> <span>  Titulo:</span>
                <input type="text" name="titulo" placeholder="Mujercitas" id="titulo"> </label>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="editorial">Editorial:</label>
            <select style="width:200px" border="1px" name="editorial" id="editorial">
                <%for (Editorial editorial : editoriales) { %>
                <option value="<%= editorial.getEditorialID() %>"><%= editorial.getNombreEditorial() %>
                </option>
                <% } %>
            </select>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="tematicas">Temática: </label>
            <select style="width:200px" border="1px" name="tematicas" id="tematicas">
                <% for (Tematica tematica : tematicas) {%>
                <option value="<%= tematica.getNombreTematica() %>"><%= tematica.getNombreTematica() %>
                </option>
                <% } %>
            </select>
        </div>
        <br><br>

        <div class="cont_forms">
            <label for="autores"><span>Autor/es:</span></label>

            <select style="width:200px" border="1px" name="autores" id="autores">
                <% for (Autor autor : autores) {%>
                <option value="<%= autor.getAutorID() %>"><%= autor.getNombreAutor() + " " + autor.getApellidoAutor() %>
                </option>
                <% } %>
            </select>
        </div>
        <br><br>
        <div class="cont_forms cont_forms_button">
            <label></label>
            <input style="background-color: #f5f5dc"   id="button_submit" type="submit" value="Añadir Libro">
        </div>
        <br><br>
    </form>
    <button onclick="location.href='/Proyecto2_Progra'">Volver</button>
</div>
<br><br>

</body>
<body>
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
        List<Editorial> editoriales2 = editorialesXMLDAO2.getEditoriales();%>

    <% AutorXMLDAO autorXMLDAO2;
        try {
            autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        List<Autor> autores2 = autorXMLDAO.getAutores();%>

    <% TematicasXMLDAO tematicasXMLDAO = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        List<Tematica> tematicas2 = tematicasXMLDAO.getTematicas();%>

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
