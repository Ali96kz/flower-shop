package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OriginProcedureAction implements Action {
    private OriginService originService = new OriginService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            originService.executeProcedure(req.getParameter("country"), req.getParameter("province"));
        } catch (ServiceException e) {
        }

        return new ActionResult(JSP_PAGE_NAME_VITRINE);
    }
}
