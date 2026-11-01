package com.bookstore.servlet;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("next", safeNext(req.getParameter("next")));
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email") == null ? "" : req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");
        String next = safeNext(req.getParameter("next"));

        try {
            User user = userDAO.login(email, password);
            if (user == null) {
                req.setAttribute("error", "Email illa password thappu");
                req.setAttribute("email", email);
                req.setAttribute("next", next);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            // Pazhaya session irundha remove panni, puthu session create pannrom (security)
            HttpSession old = req.getSession(false);
            if (old != null) {
                old.invalidate();
            }
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60); // 30 nimisham

            resp.sendRedirect(req.getContextPath() + (next.isEmpty() ? "/books" : next));
        } catch (SQLException e) {
            throw new ServletException("Database error while logging in", e);
        }
    }

    // "next" parameter-la namma site-kulla irukkura path mattum allow pannrom
    private static String safeNext(String next) {
        if (next == null || next.isEmpty()) {
            return "";
        }
        if (next.startsWith("/") && !next.startsWith("//") && !next.contains(":") && !next.contains("\\")) {
            return next;
        }
        return "";
    }
}
