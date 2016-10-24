package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserTransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    private UserTransactionService userTransactionService = new UserTransactionService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
        List<UserTransaction> userTransactionList;

        try {
            userTransactionList = userTransactionService.getAll(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user transaction list ", e);
        }

        req.setAttribute(ATTRIBUTE_NAME_TRANSACTION_LIST, userTransactionList);
        return new ActionResult(JSP_PAGE_NAME_TRANSACTION);
    }
}
