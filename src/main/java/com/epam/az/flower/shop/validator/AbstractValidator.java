package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;

import java.util.List;

public abstract class AbstractValidator implements Validator {
    private static final String WHITESPACE_REGEX = "\\s";
    private static final String EMPTY_STRING = "";
    private static final String W_REGEX = "\\W";
    private static final String D_REGEX = "\\d";
    private StringAdapter stringAdapter = new StringAdapter();

    protected void validateDate(List<String> errorMsg, String date) {
        if (date == null) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
            return;
        }

        if (!date.toString().matches(MATCH_DATE_REGEX)) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
        }

    }

    protected void validatePositiveNumber(List<String> errorMsg, String number, String message) {
        if (number == null || number.replaceAll(WHITESPACE_REGEX, EMPTY_STRING).equals(EMPTY_STRING)) {
            errorMsg.add(message);
            return;
        } else if (!number.matches(NUMBER_REGEX)) {
            errorMsg.add(message);
            return;
        }

        int value = stringAdapter.toInt(number);

        if (value <= 0) {
            errorMsg.add(message);
        }
    }

    protected void validateString(List<String> errorMsgs, String parameter, String message) {
        if (parameter.replaceAll(WHITESPACE_REGEX, EMPTY_STRING).equals(EMPTY_STRING)) {
            errorMsgs.add(message);
        } else if (parameter.matches(W_REGEX)) {
            errorMsgs.add(message);
        } else if (parameter.matches(D_REGEX)) {
            errorMsgs.add(message);
        }
    }

    protected void validateString(List<String> errorMsg, String parameter, String message, int minLength, int maxLength) {
        validateString(errorMsg, parameter, message);
        if(errorMsg.size() > 0)
            return;
        if (parameter.length() < minLength) {
            errorMsg.add(message);
        } else if (parameter.length() > maxLength) {
            errorMsg.add(message);
        }
    }
}
