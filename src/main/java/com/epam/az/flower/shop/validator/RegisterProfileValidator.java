package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RegisterProfileValidator implements Validator{

    public List<String> isValidate(HttpServletRequest request){
        List<String> errorMsg = new ArrayList<>();
        StringAdapter stringAdapter = new StringAdapter();

        String name = request.getParameter("firstName");
        String nickName = request.getParameter("nickName");
        String lastName = request.getParameter("lastName");
        Date date = stringAdapter.toSqlDate(request.getParameter("dateBirthday"));
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (date == null) {
            errorMsg.add("You insert incorrect date " +
                    "Example: 1996-12-11\n");
        }
        if(!password.equals(confirmPassword)){
            errorMsg.add("Confirm password have a different value \n");
        }
        if (nickName != null) {
            if (nickName.matches("\\W") || nickName.length() < 3) {
                errorMsg.add("You insert incorrect nick name. " +
                        "nick name must contain min 3 and max 8 characters " +
                        "name must contain A-Z,a-z,\n");
            }
        }
        if (lastName.matches("\\W") || lastName.length() < 3) {
            errorMsg.add("You insert incorrect nick name. " +
                    " Nick name must contain min 3 and max 8 characters " +
                    " Nick name must contain A-Z,a-z, \n");
        }
        if (name.matches("\\W") || name.length() < 3) {
            errorMsg.add("You insert incorrect  name. " +
                    "name must contain min 3 and max 8 characters " +
                    "name must contain A-Z,a-z, \n");
        }
        if (name.matches("\\W") || name.length() < 3) {
            errorMsg.add("You insert incorrect  name. " +
                    "name must contain min 3 and max 8 characters " +
                    "name must contain A-Z,a-z, \n");
        }

        return errorMsg;
    }
}
