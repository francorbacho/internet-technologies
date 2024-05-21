<%@ page import="pk.wieik.it.model.FCuser" %>
<%@ page import="pk.wieik.it.model.Comic" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    FCuser user = (FCuser) session.getAttribute("user");
    if (user == null) {
        user = new FCuser();
        session.setAttribute("user", user);
    }

    List<Comic> comicsEntries = (List<Comic>) session.getAttribute("comics");
    if (comicsEntries == null) {
        comicsEntries = Comic.initializeDb();
        session.setAttribute("comics", comicsEntries);
    }

    String comicIdStr = request.getParameter("id");
    if (comicIdStr == null || comicIdStr.isEmpty()) {
        comicIdStr = "0";
    }

    int comicId = Integer.parseInt(comicIdStr);
    Comic comic = comicsEntries.get(comicId);
%>

<h2><%= comic.title %></h2>

<form action="${pageContext.request.contextPath}/FC?page=edit" method="post">
    <table>
        <tr>
            <td><label for="id">ID:</label></td>
            <td><input type="text" id="id" name="id" value="<%= comicId %>" readonly></td>
        </tr>

        <tr>
            <td><label for="title">Title:</label></td>
            <td><input type="text" id="title" name="title" value="<%= comic.title %>"></td>
        </tr>

        <tr>
            <td><label for="author">Author:</label></td>
            <td><input type="text" id="author" name="author" value="<%= comic.author %>"></td>
        </tr>

        <tr>
            <td><label for="year">Year Published:</label></td>
            <td><input type="text" id="year" name="year" value="<%= comic.year %>"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>