package com.epam.az.flower.shop.servlet;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionFactory;
import com.epam.az.flower.shop.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/flower-shop/*")
public class DispatcherServlet extends HttpServlet {
    private ActionFactory actionFactory;
    private static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = null;
        try {
            action = actionFactory.getAction(req);
        } catch (ActionException e) {
            logger.error("can't initialize action class", e);
        }


        try {
            ActionResult result = action.execute(req, resp);
            doForwardOrRedirect(result, req, resp);
        } catch (Exception e) {
            logger.error("can't execute action", e);
            throw new ServletException("Cannot execute action", e);
        }

    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect(result.getView());
        } else {
            String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

}
