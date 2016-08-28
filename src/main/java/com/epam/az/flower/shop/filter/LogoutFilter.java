package com.epam.az.flower.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LogoutFilter", urlPatterns = "/logout")
public class LogoutFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletRequest;
        doFilter(request, response, filterChain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("userId") == null) {
                response.sendRedirect("login");
            }
        } else {
            response.sendRedirect("login");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
