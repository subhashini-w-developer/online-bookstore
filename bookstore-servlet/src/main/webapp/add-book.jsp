<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f6f7f9; }
        form { background: #fff; padding: 20px; max-width: 400px; border: 1px solid #ddd; }
        label { display: block; margin-top: 10px; }
        input { width: 100%; padding: 7px; box-sizing: border-box; }
        button { margin-top: 15px; padding: 8px 16px; background: #27ae60; color: #fff; border: none; border-radius: 4px; }
        .error { color: #c0392b; }
    </style>
</head>
<body>
<h1>Add New Book</h1>
<c:if test="${not empty error}"><p class="error"><c:out value="${error}"/></p></c:if>
<form action="${pageContext.request.contextPath}/addBook" method="post">
    <label>Title</label><input type="text" name="title" required>
    <label>Author</label><input type="text" name="author" required>
    <label>Category</label><input type="text" name="category">
    <label>Price (₹)</label><input type="number" step="0.01" name="price" required>
    <label>Stock</label><input type="number" name="stock" value="0" required>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/books">Cancel</a>
</form>
</body>
</html>
