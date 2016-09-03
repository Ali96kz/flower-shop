package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.UserBalance;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<UserBalance> userBalanceList = userService.getAllUserTransaction(userId);
        req.setAttribute("transactions", userBalanceList);
        return new ActionResult("transaction");
    }
}
