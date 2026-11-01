<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Online Bookstore</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f6f7f9; }
        .box { max-width: 380px; margin: 60px auto; background: #fff; padding: 28px; border: 1px solid #ddd; border-radius: 6px; }
        h1 { color: #2c3e50; margin-top: 0; font-size: 24px; }
        label { display: block; margin-top: 14px; font-size: 14px; }
        input[type=email], input[type=password] { width: 100%; padding: 9px; box-sizing: border-box; margin-top: 4px; }
        button { margin-top: 20px; width: 100%; padding: 10px; background: #27ae60; color: #fff; border: none; border-radius: 4px; font-size: 15px; cursor: pointer; }
        .error { background: #fdecea; color: #c0392b; padding: 9px; border-radius: 4px; margin-bottom: 8px; }
        .ok { background: #e8f6ee; color: #1e8449; padding: 9px; border-radius: 4px; margin-bottom: 8px; }
        .links { margin-top: 16px; font-size: 14px; text-align: center; }
    </style>
</head>
<body>
<div class="box">
    <h1>Login</h1>
    <c:if test="${param.registered == '1'}"><div class="ok">Register aayiduchu. Ippo login pannunga.</div></c:if>
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="next" value="<c:out value='${next}'/>">
        <label>Email</label>
        <input type="email" name="email" value="<c:out value='${email}'/>" required autofocus>
        <label>Password</label>
        <input type="password" name="password" required>
        <button type="submit">Login</button>
    </form>

    <div class="links">
        Account illaya? <a href="${pageContext.request.contextPath}/register">Register</a><br><br>
        <a href="${pageContext.request.contextPath}/books">&larr; Books page</a>
    </div>
</div>
</body>
</html>
