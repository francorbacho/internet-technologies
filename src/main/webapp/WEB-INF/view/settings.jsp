<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>Settings</h1>

<form method="post" action="${pageContext.request.contextPath}/FC?page=settings">
    <label>
        <span>Name:</span>
        <input type="text" name="name" class="button" required="">
    </label>

    <label>
        <span>Surname:</span>
        <input type="text" name="surname" class="button" required="">
    </label>


    <label>
        <span>Age:</span>
        <input type="number" name="age" class="button" required="">
    </label>

    <button>Login</button>
</form>
