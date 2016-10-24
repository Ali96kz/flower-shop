package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTransactionAction implements Action {
    private UserTransactionService userTransactionService = new UserTransactionService();
    private static final Logger logger = LoggerFactory.getLogger(ShowTransactionAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
        List<UserTransaction> userTransactionList;

        try {
            userTransactionList = userTransactionService.getAll(userId);
        } catch (ServiceException e) {
            logger.error("can't get transaction list", e);
            throw new ActionException("can't get user transaction list ", e);
        }

        req.setAttribute(ATTRIBUTE_NAME_TRANSACTION_LIST, userTransactionList);
        return new ActionResult(JSP_PAGE_NAME_TRANSACTION);
    }
}
