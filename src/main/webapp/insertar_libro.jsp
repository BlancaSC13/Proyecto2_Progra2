<%@page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Libro" %>
<%@page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.models.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.TematicasXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.EditorialesXMLDAO" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.webapp.session.data.AutorXMLDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<String> errores = (List<String>)request.getAttribute("check");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
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
    <title>Ingresar un libro</title>
    <% TematicasXMLDAO tematicasXmlDao = TematicasXMLDAO.abrirDocumento("tematicas.xml");
        List<Tematica> tematicass = tematicasXmlDao.getTematicas(); %>

    <%
        EditorialesXMLDAO editorialesXMLDAO = EditorialesXMLDAO.abrirDocumento("editoriales.xml");
        List<Editorial> editoriales = editorialesXMLDAO.getEditoriales(); %>

    <%
        AutorXMLDAO autorXMLDAO = AutorXMLDAO.abrirDocumento("autores.xml");
        List<Autor> autoress = autorXMLDAO.getAutores(); %>

</head>

<body>

<div id="container">
<form action="/Proyecto2_Progra/ingresar" method="post">


    <h1>Ingresar un libro</h1>

    <div class="cont_forms">
        <%
            if(errores != null && errores.size()>0){
        %>
        <ul class="alert alert-danger">
            <% for(String error : errores){%>
            <li><%=error%></li>
            <%}%>
        </ul>
        <%}%>
    </div>

        <div class="cont_forms">
            <label for="identificacion"><span>Id:</span>
            <input type="text" id="identificacion" name="identificacion" /></label>
        </div>

        <div  class="cont_forms">
            <label for="isbn"><span>ISBN:</span>
            <input type="text" placeholder="3654" name="isbn" id="isbn"/></label>
        </div>

        <div class="cont_forms">
            <label for="titulo"> <span>  Titulo:</span>
            <input type="text" name="titulo" placeholder="Mujercitas" id="titulo"> </label>
        </div>

        <div class="cont_forms">
            <label for="editorial">Editorial:</label>
                <select style="width:300px" border="1px" name="editorial" id="editorial">
                    <%for (Editorial editorial : editoriales) {    %>
                    <option value="<%= editorial.getEditorialID() %>"><%= editorial.getNombreEditorial() %>
                    </option>
                    <% } %>
                </select>
        </div>

        <div  class="cont_forms">
            <label for="tematicas">Temática: </label>
                <select style="width:300px" border="1px" name="tematicas" id="tematicas">
                    <% for (Tematica tematica : tematicass) {%>
                    <option value="<%= tematica.getNombreTematica() %>"><%= tematica.getNombreTematica() %>
                    </option>
                    <% } %>
                </select>
        </div>

        <div  class="cont_forms">
            <label for="autores"><span>Autor/es:</span></label>

                <select  style="width:300px" border="1px" name="autores" id="autores">
                    <% for (Autor autor : autoress) {%>
                    <option value="<%= autor.getAutorID() %>"><%= autor.getNombreAutor() %>
                    </option>
                    <% } %>
                </select>
        </div>

    <table id="authors-table">
        <thead>
        <tr>
            <th>idAutor</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>Nombre Autor 1</td>
            <td>Apellido Autor 1</td>
            <td>
                <button onclick="eliminarElemento(this)">Eliminar</button>
            </td>
        </tr>
        <tr>
            <td>2</td>
            <td>Nombre Autor 2</td>
            <td>Apellido Autor 2</td>
            <td>
                <button onclick="eliminarElemento(this)">Eliminar</button>
            </td>
        </tr>
        <tr>
            <td>3</td>
            <td>Nombre Autor 3</td>
            <td>Apellido Autor 3</td>
            <td>
                <button onclick="eliminarElemento(this)">Eliminar</button>
            </td>
        </tr>
        </tbody>
    </table>

    <script>
        function eliminarElemento(boton) {
            var fila = boton.parentNode.parentNode;
            fila.parentNode.removeChild(fila);
        }
    </script>


    <div class="cont_forms cont_forms_button">
        <label></label>
        <input id="button_submit" type="submit" value="Añadir Libro">
    </div>

</form>
</div>
</body>
</html>
