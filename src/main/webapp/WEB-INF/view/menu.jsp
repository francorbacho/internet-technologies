<%@ page import="pk.wieik.it.model.FCuser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    FCuser user = (FCuser) session.getAttribute("user");
    if (user == null) {
        user = new FCuser();
        session.setAttribute("user", user);
    }
%>

<ul>
    <li><a href="?page=main">Library</a></li>

    <% if (user.getPrivileges() > 0) { %>
        <li><a href="?page=settings">Settings</a></li>
    <% } %>
</ul>