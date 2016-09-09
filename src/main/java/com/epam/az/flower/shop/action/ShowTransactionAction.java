package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.service.TransactionService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    UserService userService = new UserService();
    TransactionService transactionService = new TransactionService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<UserTransaction> userTransactionList = transactionService.getAllUserTransaction(userId);
        req.setAttribute("transactions", userTransactionList);
        return new ActionResult("transaction");
    }
}
