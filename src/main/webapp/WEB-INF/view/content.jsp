<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String whatPage = "/WEB-INF/view/" + request.getParameter("page") + ".jsp"; %>
<jsp:include page="<%=whatPage%>"/>