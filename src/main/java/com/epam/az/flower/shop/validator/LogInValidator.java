package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator implements Validator {
    public static final String PARAMETER_NICK_NAME = "nickName";
    public static final String PARAMETER_PASSWORD = "password";

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        UserService userService;

        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter(PARAMETER_NICK_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        try {
            userService = new UserService();
            Integer userId = userService.getUserByCredentials(nickName, password);
            if (userId == 0) {
                errorMsg.add("password or nick name incorrect");
            }
        } catch (ServiceException e) {
            throw new ValidatorException("can't get user by id", e);
        }
        return errorMsg;
    }
}
