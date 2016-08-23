package com.epam.az.flower.shop.servlet;

import com.epam.az.flower.shop.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/gsgs")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setBalance(456);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/jsp/" + "profile" + ".jsp").forward(req, resp);

    }
}
