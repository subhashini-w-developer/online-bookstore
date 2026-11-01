
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Bookstore</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f6f7f9; }
        h1 { color: #2c3e50; margin: 0; }
        .top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 18px; }
        .user { font-size: 14px; color: #444; }
        .user form { display: inline; }
        .user .badge { background: #2c3e50; color: #fff; font-size: 11px; padding: 2px 7px; border-radius: 10px; margin-left: 4px; }
        table { border-collapse: collapse; width: 100%; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #2c3e50; color: #fff; }
        .bar { margin-bottom: 15px; }
        input[type=text] { padding: 7px; width: 250px; }
        button, .btn { padding: 7px 14px; background: #27ae60; color: #fff; border: none;
                       border-radius: 4px; text-decoration: none; cursor: pointer; font-size: 13px; }
        .del { background: #c0392b; }
        .link-btn { background: #2c3e50; }
    </style>
</head>
<body>

<div class="top">
    <h1>Online Bookstore</h1>
    <div class="user">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                Hi, <b><c:out value="${sessionScope.user.name}"/></b>
                <c:if test="${sessionScope.user.admin}"><span class="badge">ADMIN</span></c:if>
                &nbsp;
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="link-btn">Logout</button>
                </form>
            </c:when>
            <c:otherwise>
                <a class="btn link-btn" href="${pageContext.request.contextPath}/login">Login</a>
                <a class="btn" href="${pageContext.request.contextPath}/register">Register</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="bar">
    <form action="${pageContext.request.contextPath}/books" method="get" style="display:inline;">
        <input type="text" name="q" value="<c:out value='${q}'/>" placeholder="Search title / author / category">
        <button type="submit">Search</button>
    </form>
    <c:if test="${sessionScope.user.admin}">
        <a class="btn" href="${pageContext.request.contextPath}/addBook">+ Add Book</a>
    </c:if>
</div>

<table>
    <tr>
        <th>S.No</th><th>Title</th><th>Author</th><th>Category</th><th>Price (₹)</th><th>Stock</th>
        <c:if test="${sessionScope.user.admin}"><th>Action</th></c:if>
    </tr>
    <c:forEach var="b" items="${books}" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td><c:out value="${b.title}"/></td>
            <td><c:out value="${b.author}"/></td>
            <td><c:out value="${b.category}"/></td>
            <td>${b.price}</td>
            <td>${b.stock}</td>
            <c:if test="${sessionScope.user.admin}">
                <td>
                    <form action="${pageContext.request.contextPath}/deleteBook" method="post"
                          onsubmit="return confirm('Delete this book?');" style="display:inline;">
                        <input type="hidden" name="id" value="${b.id}">
                        <button type="submit" class="del">Delete</button>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    <c:if test="${empty books}">
        <tr><td colspan="7">No books found.</td></tr>
    </c:if>
</table>
</body>
</html>