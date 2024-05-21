<%@ page import="pk.wieik.it.model.FCuser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    FCuser user = (FCuser) session.getAttribute("user");
    if (user == null) {
        user = new FCuser();
        session.setAttribute("user", user);
    }
%>

<div id="login">
    <% if (user.getPrivileges() < 0) { %>
        <form method="post" action="${pageContext.request.contextPath}/FC?page=login">
            <label>
                <span>Login:</span>
                <input type="text" name="login" class="button" required="">
            </label>

            <label>
                <span>Password:</span>
                <input type="password" name="password" class="button" required="">
            </label>

            <button>Login</button>
        </form>
    <% } else { %>
        <span>You are logged in as <b><%=user.getLogin()%></b> with privileges: <b><%= user.getPrivileges() %></b> </span>
        <form method="post" action="${pageContext.request.contextPath}/FC?page=logout">
            <button>Logout</button>
        </form>
    <% } %>
</div>
