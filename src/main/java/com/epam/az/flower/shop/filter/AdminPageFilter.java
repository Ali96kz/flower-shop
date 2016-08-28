package com.epam.az.flower.shop.filter;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminPageFilter", urlPatterns = "/flower-shop/admin")
public class AdminPageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        doFilter(request, response, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        UserService userService = new UserService();

        if (session == null) {
            response.sendRedirect("login");
            return;
        } else if (session.getAttribute("userId") == null) {
            response.sendRedirect("login");
            return;
        }
        User user = userService.getUserByID((Integer) session.getAttribute("userId"));
        if (!user.getRole().name.equals("admin")) {
            response.sendRedirect("profile");
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
