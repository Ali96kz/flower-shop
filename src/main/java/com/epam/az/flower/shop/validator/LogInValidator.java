package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator implements Validator {

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        UserService userService = new UserService();
        List<String> errorMsg = new ArrayList<>();
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        Integer userId = userService.getUserByCredentials(nickName, password);

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
