package com.epam.az.flower.shop.filter;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class UserFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(UserFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletRequest, filterChain);
        } catch (FilterException e) {
            logger.trace(" can't do filter",e);
        }
    }
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, FilterException {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new FilterException("can;t initialize service", e);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("userId")!= null) {
                try {
                    User user = userService.findById((Integer) session.getAttribute("userId"));
                    if (user.getDeleteDay() != null) {
                        response.sendRedirect("delete-profile");
                        return;
                    }
                } catch (ServiceException e) {
                    throw new FilterException("can't get user from service", e);
                }
            }
        }
        filterChain.doFilter(request, response);

    }
    @Override
    public void destroy() {

    }
}
