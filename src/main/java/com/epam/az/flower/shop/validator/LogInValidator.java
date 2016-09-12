package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator implements Validator {

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        UserService userService;
        Integer userId;
        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        try {
            userService = new UserService();
            userId = userService.getUserByCredentials(nickName, password);
        } catch (ServiceException e) {
            throw new ValidatorException("can't get user by id", e);
        }

        if (nickName.matches("\\W")) {
            errorMsg.add("nick name contain incorrect characters");

        }

        if (password.length() <  6 ) {
            errorMsg.add("password must contain 6 or 16 more characters");
        }

        if (userId == null) {
            errorMsg.add("you insert incorrect nickname or password");
        }

        return errorMsg;
    }
}
