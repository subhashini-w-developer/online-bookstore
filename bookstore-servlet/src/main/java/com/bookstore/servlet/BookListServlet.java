package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("q");
        try {
            List<Book> books;
            if (keyword != null && !keyword.trim().isEmpty()) {
                books = bookDAO.searchBooks(keyword.trim());
            } else {
                books = bookDAO.getAllBooks();
            }
            req.setAttribute("books", books);
            req.setAttribute("q", keyword == null ? "" : keyword);
            req.getRequestDispatcher("/books.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Database error while loading books", e);
        }
    }
}
