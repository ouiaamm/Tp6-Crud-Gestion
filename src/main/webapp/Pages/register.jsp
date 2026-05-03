<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card p-4" style="width: 400px;">

    <h3 class="text-center mb-3">Register</h3>

    <form action="doRegister" method="post">

        <input type="text" name="username" class="form-control mb-2" placeholder="Username" required>

        <input type="password" name="password" class="form-control mb-2" placeholder="Password" required>

        <input type="password" name="confirmPassword" class="form-control mb-2" placeholder="Confirm Password" required>

        <button class="btn btn-primary w-100">Sign up</button>

    </form>

    <c:if test="${error != null}">
        <p class="text-danger text-center mt-2">${error}</p>
    </c:if>

    <div class="text-center mt-3">
        <a href="login">Already have an account? Login</a>
    </div>

</div>

</body>
</html>