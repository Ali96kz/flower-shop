package com.epam.az.flower.shop.action;


import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.validator.LogInValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private Validator validator = new LogInValidator();
    private UserService userService = new UserService();
    private Hasher hasher = new Hasher();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            if (!isValidate(req)) {
                return new ActionResult(JSP_PAGE_NAME_LOGIN);
            }

            HttpSession session = req.getSession();
            String nickName = req.getParameter(PARAMETER_NICK_NAME);
            String password = req.getParameter(PARAMETER_PASSWORD);

            int userId = userService.getUserIdByCredentials(nickName, hasher.hash(password));
            session.setAttribute(SESSION_PARAMETER_USER_ID, userId);
            logger.info("put user id into session {}", String.valueOf(session.getAttribute(SESSION_PARAMETER_USER_ID)));
            return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        } catch (ServiceException e) {
            logger.error("can't get user from data", e);
            throw new ActionException("can't get user from data", e);
        }
    }

    public boolean isValidate(HttpServletRequest req) throws ActionException {
        try {
            List<String> errorMsg = validator.isValidate(req);
            if (errorMsg.size() > 0) {
                req.setAttribute(ATTRIBUTE_ERROR_MSG, errorMsg);
                return false;
            }
            return true;
        } catch (ValidatorException e) {
            throw new ActionException("problem with validating ", e);
        }
    }
}
