package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.TransactionService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserCountPageActio implements Action {
    private TransactionService transactionService = new TransactionService();
    private UserService userService = new UserService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            req.setAttribute("users", userService.getAll());
            req.setAttribute("transactions", transactionService.getAll());
            return new ActionResult(JSP_COUNT_ADD_MONEY);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
    }
}
