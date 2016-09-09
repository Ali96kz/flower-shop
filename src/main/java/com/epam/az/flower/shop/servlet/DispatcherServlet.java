package com.epam.az.flower.shop.servlet;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/flower-shop/*")
public class DispatcherServlet extends HttpServlet {
    ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = actionFactory.getAction(req);
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }

        ActionResult result;
        try {
            result = action.execute(req, resp);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }

        doForwardOrRedirect(result, req, resp);
    }
    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()){
            resp.sendRedirect(result.getView());
        } else {
            String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

}
