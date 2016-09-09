package com.epam.az.flower.shop.filter;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/flower-shop/*")
public class SecurityFilter implements Filter {

    private List<String> anonymousUserViews;
    private List<String> userViews;
    private List<String> managerViews;
    private List<String> adminViews;
    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        anonymousUserViews = new ArrayList<>();
        adminViews = new ArrayList<>();

        anonymousUserViews.add("/vitrine");
        anonymousUserViews.add("/basket");
        anonymousUserViews.add("/main");
        anonymousUserViews.add("/login");
        anonymousUserViews.add("/registration");
        anonymousUserViews.add("/product-inf");
        anonymousUserViews.add("/product-in-basket");

        userViews = new ArrayList<>(anonymousUserViews);
        userViews.remove("registration");

        userViews.add("/profile");
        userViews.add("/addMoneyToBalance");
        userViews.add("/logout");
        userViews.add("/buy-all-basket");
        userViews.add("/buy-product");
        userViews.add("/transaction");
        userViews.add("/cash");
        userViews.add("/edit-profile");
        userViews.add("/delete-profile");

        managerViews = new ArrayList<>(userViews);
        managerViews.add("/add-product");
        managerViews.add("/edit-product");
        managerViews.add("/delete-product");

        adminViews = new ArrayList<>(managerViews);
        adminViews.add("/admin");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        doFilter(req, resp, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String contextPath = request.getPathInfo();
        if (userId == null) {
            if (!anonymousUserViews.contains(contextPath)) {
                response.sendRedirect("login");
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        User user = userService.findByID(userId);

        if (!getViewsForRole(user.getUserRole()).contains(contextPath)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        } else {
            List<String> views = getViewsForRole(user.getUserRole());

            if (!views.contains(contextPath)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You haven't permissions");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private List<String> getViewsForRole(UserRole role) {
        List<String> views;
        switch (role.getName()) {
            case "admin":
                views = adminViews;
                break;
            case "manager":
                views = managerViews;
                break;
            default:
                views = userViews;
                break;
        }
        return views;
    }

    @Override
    public void destroy() {

    }
}
