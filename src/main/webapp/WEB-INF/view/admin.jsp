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
%>

