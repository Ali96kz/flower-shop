package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.TransactionService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    UserService userService;
    TransactionService transactionService;

    public ShowTransactionAction() throws ActionException {
        try {
            transactionService = new TransactionService();
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize ", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<UserTransaction> userTransactionList = null;
        try {
            userTransactionList = transactionService.getAllUserTransaction(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user transaction list ", e);
        }
        req.setAttribute("transactions", userTransactionList);
        return new ActionResult("transaction");
    }
}
