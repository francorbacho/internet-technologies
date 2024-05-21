<%@ page import="pk.wieik.it.model.FCuser" %>
<%@ page import="pk.wieik.it.model.Tools" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    FCuser user = (FCuser) session.getAttribute("user");
    if (user == null) {
        user = new FCuser();
        session.setAttribute("user", user);
    }
    String whatPage = request.getParameter("page");
    if (user.getPrivileges() == 1) {
        whatPage = Tools.parsePage(whatPage, "main;third;settings;favourite");
    } else {
        whatPage = Tools.parsePage(whatPage, "main;third");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <title>IT-Lab4</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
<div id="container">
    <div id="header">
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </div>
    <div id="middle">
        <div id="menu">
            <jsp:include page="/WEB-INF/view/menu.jsp"/>

            <jsp:include page="/WEB-INF/view/login.jsp"/>
        </div>
        <div id="content">
            <jsp:include page="/WEB-INF/view/content.jsp">
                <jsp:param name="page" value="<%=whatPage%>"/>
            </jsp:include>
        </div>
    </div>
    <div id="footer">
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>