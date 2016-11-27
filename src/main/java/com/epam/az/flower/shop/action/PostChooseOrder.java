package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.dao.manager.Util;
import com.epam.az.flower.shop.entity.UserOrder;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.util.UtilClassException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PostChooseOrder implements Action {
    private OrderService orderService = new OrderService();
    private StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException{
        String date = req.getParameter("date");
        int userId = Integer.parseInt(req.getParameter("userId"));
        try {
            List<UserOrder> orders = orderService.chooseOrder(userId, stringAdapter.toSqlDate(date));
            req.setAttribute("userOrders", orders);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (UtilClassException e) {
            e.printStackTrace();
        }
        return new ActionResult(CHOOSE_ORDER);
    }
}
