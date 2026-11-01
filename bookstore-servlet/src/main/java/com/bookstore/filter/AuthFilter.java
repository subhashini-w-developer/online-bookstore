package com.bookstore.filter;

import com.bookstore.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Add / Delete pages-ku ADMIN mattum poga mudiyum.
 * Login pannala na -> login page-ku anuppum.
 * Login panni irundhu ADMIN illana -> 403 Forbidden.
 */
@WebFilter(urlPatterns = {"/addBook", "/deleteBook"})
public class AuthFilter implements Filter {

    // Jetty 9.4 Servlet 3.1 API use pannum. Adhula init() and destroy() kandippa irukkanum,
    // illana app start aagaama 503 error varum.
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session == null) ? null : (User) session.getAttribute("user");

        if (user == null) {
            // POST request-a login-ku piragu GET-aa replay panna koodaadhu, so books page-ku anuppurom
            String next = "GET".equals(req.getMethod()) ? "/addBook" : "/books";
            resp.sendRedirect(req.getContextPath() + "/login?next=" + next);
            return;
        }
        if (!user.isAdmin()) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin mattum idha use panna mudiyum");
            return;
        }
        chain.doFilter(request, response);
    }
}
