package com.epam.az.flower.shop.action.vitrine;


import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountUsersAndTransactions implements Action {
    private OriginService originService = new OriginService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int userId = Integer.parseInt(req.getParameter(PARAMETER_USER_IDS));
            int transactionId = Integer.parseInt(req.getParameter(ATTRIBUTE_NAME_TRANSACTION_ID));
            int sum = originService.countAddMoney(userId, transactionId);
            req.setAttribute(ATTRIBUTE_NAME_SUM, sum);
            return new ActionResult(JSP_COUNT_ADD_MONEY);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
    }
}
