package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.TransactionService;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.service.UserTransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    UserService userService;
    UserTransactionService userTransactionService;

    public ShowTransactionAction() throws ActionException {
        try {
            userTransactionService = new UserTransactionService();
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize ", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<UserTransaction> userTransactionList ;
        try {
            userTransactionList = userTransactionService.getAll(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user transaction list ", e);
        }
        req.setAttribute("transactions", userTransactionList);
        return new ActionResult("transaction");
    }
}
