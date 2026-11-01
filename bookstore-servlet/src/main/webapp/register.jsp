<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Online Bookstore</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f6f7f9; }
        .box { max-width: 380px; margin: 50px auto; background: #fff; padding: 28px; border: 1px solid #ddd; border-radius: 6px; }
        h1 { color: #2c3e50; margin-top: 0; font-size: 24px; }
        label { display: block; margin-top: 14px; font-size: 14px; }
        input[type=text], input[type=email], input[type=password] { width: 100%; padding: 9px; box-sizing: border-box; margin-top: 4px; }
        button { margin-top: 20px; width: 100%; padding: 10px; background: #27ae60; color: #fff; border: none; border-radius: 4px; font-size: 15px; cursor: pointer; }
        .error { background: #fdecea; color: #c0392b; padding: 9px; border-radius: 4px; margin-bottom: 8px; }
        .links { margin-top: 16px; font-size: 14px; text-align: center; }
        small { color: #777; }
    </style>
</head>
<body>
<div class="box">
    <h1>Create Account</h1>
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <label>Name</label>
        <input type="text" name="name" value="<c:out value='${name}'/>" required autofocus>
        <label>Email</label>
        <input type="email" name="email" value="<c:out value='${email}'/>" required>
        <label>Password <small>(kuraindhadhu 6 characters)</small></label>
        <input type="password" name="password" minlength="6" required>
        <label>Confirm Password</label>
        <input type="password" name="confirm" minlength="6" required>
        <button type="submit">Register</button>
    </form>

    <div class="links">
        Already account irukka? <a href="${pageContext.request.contextPath}/login">Login</a>
    </div>
</div>
</body>
</html>
