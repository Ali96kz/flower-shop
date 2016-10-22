package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RegisterProfileValidator extends AbstractValidator {
    public static final String INCORRECT_DATE_ERROR_MSG = "You insert incorrect date Example: 1996-12-11";
    public static final String DIFFERENT_PASSWORD_ERROR_MSG = "Confirm password has a different value";
    public static final String BUSY_NICKNAME_ERROR_MSG = "This nickname is busy, please insert another nickname";
    public static final String MATCH_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String PARAMETER_FIRST_NAME = "firstName";
    private static final String PARAMETER_NICK_NAME = "nickName";
    private static final String PARAMETER_LAST_NAME = "lastName";
    private static final String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    private static final String ATTRIBUTE_FIRST_NAME = "first name";
    private static final String ATTRIBUTE_NAME_LAST_NAME = "last name";
    private static final String ATTRIBUTE_NAME_NICK_NAME = "nick name";
    private static final String ATTRIBUTE_NAME_CONFIRM_PASSWORD = "confirm password";
    private static final String ATTRIBUTE_NAME_PASSWORD = "password";
    private static final int NICKNAME_MAX_LENGTH = 16;
    private static final int NICKNAME_MIN_LENGTH = 3;
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 12;
    private static final int LAST_NAME_MAX_LENGTH = 16;
    private static final int LAST_NAME_MIN_LENGTH = 3;
    private static final int FIRST_NAME_MAX_LENGTH = 16;
    private static final int FIRST_NAME_MIN_LENGTH = 3;
    private UserService userService = new UserService();

    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        StringAdapter stringAdapter = new StringAdapter();

        String name = request.getParameter(PARAMETER_FIRST_NAME);
        String nickName = request.getParameter(PARAMETER_NICK_NAME);
        String lastName = request.getParameter(PARAMETER_LAST_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String confirmPassword = request.getParameter(PARAMETER_CONFIRM_PASSWORD);
        Date date = stringAdapter.toSqlDate(request.getParameter(PARAMETER_DATE_BIRTHDAY));

        try {
            boolean isFree = userService.isFree(nickName);
            if (!isFree) {
                errorMsg.add(BUSY_NICKNAME_ERROR_MSG);
            }
        } catch (ServiceException e) {
            throw new ValidatorException("can't validate nickname", e);
        }

        if (date == null) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
            return errorMsg;
        }
        if (!date.toString().matches(MATCH_DATE_REGEX)) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
        }

        validateString(errorMsg, name, ATTRIBUTE_FIRST_NAME, FIRST_NAME_MIN_LENGTH, FIRST_NAME_MAX_LENGTH);
        validateString(errorMsg, lastName, ATTRIBUTE_NAME_LAST_NAME, LAST_NAME_MIN_LENGTH, LAST_NAME_MAX_LENGTH);
        validateString(errorMsg, nickName, ATTRIBUTE_NAME_NICK_NAME, NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH);
        validateString(errorMsg, password, ATTRIBUTE_NAME_PASSWORD, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);
        validateString(errorMsg, confirmPassword, ATTRIBUTE_NAME_CONFIRM_PASSWORD, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);

        if (!password.equals(confirmPassword)) {
            errorMsg.add(DIFFERENT_PASSWORD_ERROR_MSG);
        }

        return errorMsg;
    }
}
