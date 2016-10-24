package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.Hasher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator extends AbstractValidator {

    private Hasher hasher = new Hasher();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        UserService userService;

        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter(PARAMETER_NICK_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        validateString(errorMsg, nickName, NICK_NAME, NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH);
        validateString(errorMsg, password, PARAMETER_PASSWORD, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);
        if (errorMsg.size() != 0) {
            return errorMsg;
        }

        try {

            Integer userId;
            userService = new UserService();
            userId = userService.getUserIdByCredentials(nickName, hasher.hash(password));

            if (userId == null || userId == 0) {
                errorMsg.add(PASSWORD_OR_NICK_NAME_INCORRECT);
            }
        } catch (ServiceException e) {
            throw new ValidatorException("can't get user by id", e);
        }
        return errorMsg;
    }
}
