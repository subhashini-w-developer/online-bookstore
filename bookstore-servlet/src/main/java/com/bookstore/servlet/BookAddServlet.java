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

@WebServlet("/addBook")
public class BookAddServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add-book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Book book = new Book(
                    req.getParameter("title"),
                    req.getParameter("author"),
                    req.getParameter("category"),
                    Double.parseDouble(req.getParameter("price")),
                    Integer.parseInt(req.getParameter("stock")));
            bookDAO.addBook(book);
            resp.sendRedirect(req.getContextPath() + "/books");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Price and stock must be valid numbers");
            req.getRequestDispatcher("/add-book.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Database error while adding book", e);
        }
    }
}
