package com.bookstore.servlet;

import com.bookstore.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = trim(req.getParameter("name"));
        String email = trim(req.getParameter("email")).toLowerCase();
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        String error = null;
        if (name.isEmpty()) {
            error = "Name kudunga";
        } else if (!EMAIL.matcher(email).matches()) {
            error = "Correct email kudunga";
        } else if (password == null || password.length() < 6) {
            error = "Password kuraindhadhu 6 characters irukkanum";
        } else if (!password.equals(confirm)) {
            error = "Rendu password-um match aagala";
        }

        try {
            if (error == null && !userDAO.registerUser(name, email, password)) {
                error = "Indha email already register aagi irukku";
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while registering", e);
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.setAttribute("name", name);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/login?registered=1");
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
