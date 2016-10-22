package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;

import java.util.List;

public abstract class AbstractValidator implements Validator {
    public static final String YOU_DIDN_T_INSERT = "You didn't insert ";
    public static final String COULDN_T_BE_BELOW_ZERO = "couldn't be <= 0";
    public static final String PLEASE_INSERT = "please insert ";
    public static final String MUST_CONTAIN_MIN = " must contain min ";
    public static final String MAX = " max ";
    public static final String NAME_MUST_CONTAIN = " must contain just name must contain A-Z,a-z, white space";
    public static final String INCORRECT = "incorrect, ";
    public static final String CAN_T_CONTAIN_A_NUMBER = " can't contain a number";
    public static final String CAN_T_CONTAIN_JUST_WHITE_SPACE = " can't contain just white space";
    private static final String NUMBER_REGEX = "[0-9]+";
    private StringAdapter stringAdapter = new StringAdapter();

    protected void validatePositiveNumber(List<String> errorMsg, String number, String name) {
        if (number == null || number.replaceAll("\\s", "").equals("")) {
            errorMsg.add(YOU_DIDN_T_INSERT + name);
            return;
        }
        if (!number.matches(NUMBER_REGEX)) {
            errorMsg.add(PLEASE_INSERT + name);
            return;
        }

        int value = stringAdapter.toInt(number);

        if (value <= 0) {
            errorMsg.add(name + COULDN_T_BE_BELOW_ZERO);
        }

    }

    protected void validateString(List<String> errorMsg, String parameter, String name) {
        if (parameter.replaceAll("\\s", "").equals("")) {
            errorMsg.add(name + CAN_T_CONTAIN_JUST_WHITE_SPACE);
        }
        if (parameter.matches("\\W")) {
            errorMsg.add(INCORRECT + name + NAME_MUST_CONTAIN);
        }

        if (parameter.matches("\\d")) {
            errorMsg.add(name + CAN_T_CONTAIN_A_NUMBER);
        }
    }

    protected void validateString(List<String> errorMsg, String parameter, String name, int minLength, int maxLength) {
        validateString(errorMsg, parameter, name);
        if (parameter.length() < minLength) {
            errorMsg.add(name + MUST_CONTAIN_MIN + minLength + MAX + maxLength);
        } else if (parameter.length() > maxLength) {
            errorMsg.add(name + MUST_CONTAIN_MIN + minLength + MAX + maxLength);
        }
    }
}
