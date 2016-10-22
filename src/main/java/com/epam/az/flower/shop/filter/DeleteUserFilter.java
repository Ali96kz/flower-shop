package com.epam.az.flower.shop.filter;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "DeleteUserFilter", urlPatterns = "/flower-shop/*")
public class DeleteUserFilter implements Filter {
    public static final String SESSION_ATTRIBUTE_USER_ID = "userId";
    public static final String URL_LOGIN = "login";
    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
        } catch (FilterException e) {
            e.printStackTrace();
        }
    }

    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse response, FilterChain filterChain) throws FilterException, IOException, ServletException {
        HttpSession session = servletRequest.getSession(false);

        if (session != null) {
            Integer userId = (Integer) session.getAttribute(SESSION_ATTRIBUTE_USER_ID);
            if (userId != null) {

                try {
                    User user = userService.findById(userId);
                    if (user.getDeleteDay() != null) {
                        response.sendRedirect(URL_LOGIN);
                        return;
                    }

                } catch (ServiceException | IOException e) {
                    throw new FilterException("can't find user by id", e);
                }
            }
        }
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
