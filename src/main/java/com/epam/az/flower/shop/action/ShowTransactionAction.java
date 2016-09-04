package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.UserBalance;
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
        List<UserBalance> userBalanceList = transactionService.getAllUserTransaction(userId);
        req.setAttribute("transactions", userBalanceList);
        return new ActionResult("transaction");
    }
}
