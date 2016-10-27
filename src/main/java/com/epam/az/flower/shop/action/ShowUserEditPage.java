package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserEditPage extends AbstractUserAction {
    private static final Logger logger = LoggerFactory.getLogger(ShowUserEditPage.class);
    private OriginService originService = new OriginService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            setUserBySessionId(req);
            req.setAttribute(ATTRIBUTE_NAME_ORIGIN_LIST, originService.getAll());

            return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
        } catch (ServiceException e) {
            logger.error("can't get user", e);
            throw new ActionException("can't get user", e);
        }
    }
}
