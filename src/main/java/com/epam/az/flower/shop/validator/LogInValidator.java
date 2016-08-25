package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator implements Validator {

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        String nickname = request.getParameter("nickName");
        String password = request.getParameter("password");
        if (nickname.matches("\\W")) {
            errorMsg.add("nick name contain incorrect characters");
        }
        if (password.length() <  6 ) {
            errorMsg.add("password must contain 6 or 16 more characters");
        }
        return errorMsg;
    }
}
