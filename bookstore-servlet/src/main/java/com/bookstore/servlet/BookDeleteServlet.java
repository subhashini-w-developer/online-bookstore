package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBook")
public class BookDeleteServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    // POST use panrom (GET-la delete pannina safe illa)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            bookDAO.deleteBook(id);
            resp.sendRedirect(req.getContextPath() + "/books");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book id");
        } catch (SQLException e) {
            throw new ServletException("Database error while deleting book", e);
        }
    }
}
