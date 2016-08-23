package com.epam.az.flower.shop.servlet;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.factory.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Executable;

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
            req.getRequestDispatcher("/WEB-INF/jsp/" + result.getView() + ".jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }
    }

}
