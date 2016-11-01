package com.epam.az.flower.shop.filter;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/flower-shop/*")
public class SecurityFilter implements Filter {
    public static final String URL_LOGIN = "login";
    public static final String USER_ROLE_ADMIN = "admin";
    public static final String USER_ROLE_MANAGER = "manager";
    public static final String USER_ROLE_CUSTOMER = "customer";
    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    public static final String SESSION_ATTRIBUTE_USER_ID = "userId";
    private List<String> anonymousUserViews;
    private List<String> userViews;
    private List<String> managerViews;
    private List<String> adminViews;

    public void initAnonymousUser() {
        anonymousUserViews = new ArrayList<>();
        anonymousUserViews.add("/vitrine");
        anonymousUserViews.add("/set-language");
        anonymousUserViews.add("/basket");
        anonymousUserViews.add("/main");
        anonymousUserViews.add("/about-project");
        anonymousUserViews.add("/contact");
        anonymousUserViews.add("/login");
        anonymousUserViews.add("/delete-product-basket");
        anonymousUserViews.add("/registration");
        anonymousUserViews.add("/product-inf");
        anonymousUserViews.add("/product-in-basket");
        anonymousUserViews.add("/add-product");
    }

    public void initUser() {
        userViews = new ArrayList<>(anonymousUserViews);

        userViews.remove("/registration");
        userViews.remove("/login");

        userViews.add("/delete-account");
        userViews.add("/profile");
        userViews.add("/delete-profile");
        userViews.add("/edit-user");
        userViews.add("/addMoneyToBalance");
        userViews.add("/logout");
        userViews.add("/buy-all-basket");
        userViews.add("/buy-product");
        userViews.add("/transaction");
        userViews.add("/cash");
        userViews.add("/edit-account");
        userViews.add("/delete-profile");
    }

    public void initManagerViews() {
        managerViews = new ArrayList<>(userViews);
        managerViews.add("/edit-product");
        managerViews.add("/delete-product");
        managerViews.add("/manager");
        managerViews.add("/add-product");

    }

    public void initAdminViews() {
        adminViews = new ArrayList<>(managerViews);
        adminViews.add("/admin");
        adminViews.add("/delete-user");
        adminViews.add("/admin-registration");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initAnonymousUser();
        initUser();
        initManagerViews();
        initAdminViews();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            doFilter(req, resp, chain);
        } catch (FilterException e) {
            logger.trace("can't filter request", e);
        }
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, FilterException {
        Integer userId = (Integer) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER_ID);
        String contextPath = request.getPathInfo();
        if (userId == null) {
            if (!anonymousUserViews.contains(contextPath)) {
                response.sendRedirect(URL_LOGIN);
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        User user;

        try {
            UserService userService = new UserService();
            user = userService.findById(userId);
        } catch (ServiceException e) {
            logger.error("can't find user role by id", e);
            throw new FilterException("can't get user by id", e);
        }

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
            case USER_ROLE_ADMIN:
                views = adminViews;
                break;
            case USER_ROLE_MANAGER:
                views = managerViews;
                break;
            case USER_ROLE_CUSTOMER:
                views = userViews;
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
