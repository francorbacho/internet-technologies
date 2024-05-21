<%@ page import="pk.wieik.it.model.FCuser" %>
<%@ page import="pk.wieik.it.model.Comic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    FCuser user = (FCuser) session.getAttribute("user");
    if (user == null) {
        user = new FCuser();
        session.setAttribute("user", user);
    }

    ServletContext context = request.getServletContext();

    List<Comic> comicsEntries = (List<Comic>) context.getAttribute("comics");
    if (comicsEntries == null) {
        comicsEntries = Comic.initializeDb();
        context.setAttribute("comics", comicsEntries);
    }
%>

<form action="${pageContext.request.contextPath}?page=main" method="get">
    <label for="filterYear">Filter by Year:</label>
    <input type="text" id="filterYear" name="filterYear">
    <br>
    <% if (user.getPrivileges() == 1) { %>
        <input type="checkbox" id="showFavorites" name="showFavorites" value="true">
        <label for="showFavorites">Show only Favorites</label>
        <br>
    <% } %>

    <input type="submit" value="Filter">
</form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Year</th>
        <th>Title</th>
        <th>Author</th>
        <th>Release Date</th>
        <th>Add to Favorites</th>
        <% if (user.getPrivileges() == 2) { %>
        <th>Edit</th>
        <% } %>
    </tr>
    <%
        String filterYear = request.getParameter("filterYear");
        String showFavoritesStr = request.getParameter("showFavorites");
        boolean showFavourites = showFavoritesStr != null && showFavoritesStr.equals("true");
    %>

    <% for (int comicEntryId = 0; comicEntryId < comicsEntries.size(); comicEntryId++) {
        Comic comicEntry = comicsEntries.get(comicEntryId);
        if (showFavourites && !user.getFavourites().contains(comicEntryId)) {
            continue;
        }

        if (filterYear == null || filterYear.isEmpty() || filterYear.equals(comicEntry.year + "")) {
    %>
    <tr>
        <td><%= comicEntryId %></td>
        <td><%= comicEntry.year %></td>
        <td><%= comicEntry.title %></td>
        <td><%= comicEntry.author %></td>
        <td><%= comicEntry.dateAdded %></td>
        <td>
            <input
                type="checkbox"
                <%= user.getFavourites().contains(comicEntryId) ? "checked=1" : "" %>
                <%= user.getPrivileges() != 1 ? "disabled=\"disabled\"" : "" %>
                onclick="toggleFavorite('<%= comicEntryId %>')"
            >
        </td>
        <% if (user.getPrivileges() == 2) { %>
        <td>
            <a href="${pageContext.request.contextPath}?page=edit&id=<%= comicEntryId %>">Edit</a>
        </td>
        <% } %>
    </tr>
    <% }} %>
</table>
