package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.Hasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator extends AbstractValidator {

    private static Logger logger = LoggerFactory.getLogger(LogInValidator.class);
    private Hasher hasher = new Hasher();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {

        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter(PARAMETER_NICK_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        validateString(errorMsg, nickName, ERROR_INCORRECT_NICK_NAME, NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH);
        validateString(errorMsg, password, ERROR_INCORRECT_PASSWORD, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);

        if (errorMsg.size() != 0) {
            return errorMsg;
        }
        isUserExist(errorMsg, nickName, password);

        return errorMsg;
    }

    private void isUserExist(List<String> errorMsg, String nickName, String password) throws ValidatorException {
        UserService userService;
        try {
            Integer userId;
            userService = new UserService();
            userId = userService.getUserIdByCredentials(nickName, hasher.hash(password));

            if (userId == null || userId == 0) {
                errorMsg.add(ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("can't get user by id", e);
            throw new ValidatorException("can't get user by id", e);
        }

    }

}
