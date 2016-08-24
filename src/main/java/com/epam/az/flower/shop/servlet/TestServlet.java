package com.epam.az.flower.shop.servlet;

import com.epam.az.flower.shop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("result",
                "Employee Not Inserted: " + request.getAttribute("error"));
        RequestDispatcher view = request
                .getRequestDispatcher("displayEmployee.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/" + "registration" + ".jsp").forward(req, resp);
    }
}
