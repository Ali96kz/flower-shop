package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;

import java.util.List;

public abstract class AbstractValidator implements Validator {
    public static final String NUMBER_REGEX = "[0-9]+";
    private StringAdapter stringAdapter = new StringAdapter();

    protected void validatePositiveNumber(List<String> errorMsg, String number, String name) {
        if (number == null || number.replaceAll("\\s", "").equals("")) {
            errorMsg.add("You didn't insert " + name);
            return;
        }
        if (!number.matches(NUMBER_REGEX)) {
            errorMsg.add("please insert " + name);
            return;
        }

        int value = stringAdapter.toInt(number);

        if (value <= 0) {
            errorMsg.add(name + "couldn't be <= 0");
        }

    }

    protected void validateString(List<String> errorMsg, String parameter, String name) {
        if (parameter.replaceAll("\\s", "").equals("")) {
            errorMsg.add(name + " can't contain just white space");
        }
        if (parameter.matches("\\W")) {
            errorMsg.add("incorrect, " + name + " must contain just name must contain A-Z,a-z, white space");
        }

        if (parameter.matches("\\d")) {
            errorMsg.add(name + " can't contain a number");
        }
    }

    protected void validateString(List<String> errorMsg, String parameter, String name, int minLength, int maxLength) {
        validateString(errorMsg, parameter, name);
        if (parameter.length() < minLength) {
            errorMsg.add(name + " must contain min " + minLength + " max " + maxLength);
        } else if (parameter.length() > maxLength) {
            errorMsg.add(name + " must contain min " + minLength + " max " + maxLength);
        }
    }
}
