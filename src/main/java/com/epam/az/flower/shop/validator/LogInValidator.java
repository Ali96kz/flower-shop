package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.Hasher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator extends AbstractValidator {

    public static final int NICKNAME_MAX_LENGTH = 16;
    public static final int NICKNAME_MIN_LENGTH = 3;

    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 12;

    public static final String PARAMETER_NICK_NAME = "nickName";
    public static final String PARAMETER_PASSWORD = "password";
    private Hasher hasher = new Hasher();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        UserService userService;

        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter(PARAMETER_NICK_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        validateString(errorMsg, nickName, "nick name", NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH);
        validateString(errorMsg, password, "password", PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);
        if (errorMsg.size() != 0) {
            return errorMsg;
        }

        try {

            Integer userId;
            userService = new UserService();
            userId = userService.getUserIdByCredentials(nickName, hasher.hash(password));

            if (userId == null || userId == 0) {
                errorMsg.add("password or nick name incorrect");
            }
        } catch (ServiceException e) {
            throw new ValidatorException("can't get user by id", e);
        }
        return errorMsg;
    }
}
