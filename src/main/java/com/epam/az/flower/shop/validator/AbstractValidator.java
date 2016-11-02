package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;

import java.util.List;

public abstract class AbstractValidator implements Validator {
    private static final String WHITESPACE_REGEX = "\\s";
    private static final String EMPTY_STRING = "";
    private static final String W_REGEX = "\\W";
    private static final String D_REGEX = "\\d";
    private StringAdapter stringAdapter = new StringAdapter();

    protected void validateDate(List<String> errorMsg, String date){
        if (date == null) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
            return ;
        }

        if (!date.toString().matches(MATCH_DATE_REGEX)) {
            errorMsg.add(INCORRECT_DATE_ERROR_MSG);
        }

    }
    protected void validatePositiveNumber(List<String> errorMsg, String number, String name) {
        if (number == null || number.replaceAll(WHITESPACE_REGEX, EMPTY_STRING).equals(EMPTY_STRING)) {
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
        if (parameter.replaceAll(WHITESPACE_REGEX, EMPTY_STRING).equals(EMPTY_STRING)) {
            errorMsg.add(name + CAN_T_CONTAIN_JUST_WHITE_SPACE);
        }

        if (parameter.matches(W_REGEX)) {
            errorMsg.add(INCORRECT + name + NAME_MUST_CONTAIN_ERROR);
        }

        if (parameter.matches(D_REGEX)) {
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
