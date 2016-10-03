package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;

import java.util.List;

public abstract class AbstractValidator implements Validator{
    public static final String NUMBER_REGEX = "^[1-9][0-9]{1,8}$";
    private StringAdapter stringAdapter = new StringAdapter();
    public void validatePositiveNumber(List<String> errorMsg, String number, String name) {
        //TODO
        if (number == null || number ==  "") {
            errorMsg.add("please insert "+name);
        }

        int  value =  stringAdapter.toInt(number);

        if (value <= 0 ){
            errorMsg.add(name+"couldn't be <= 0");
        }

    }

    public void validateString(List<String> errorMsg, String parameter, String name){
        if (parameter == null || parameter.matches(NUMBER_REGEX)) {
            errorMsg.add("please insert "+name);
        }
        if (parameter.matches("\\W")) {
            errorMsg.add("incorrect ,"+name+"must contain just name must contain A-Z,a-z, white space");
        }
    }

    public void validateString(List<String> errorMsg, String parameter, String name, int minLength, int maxLength){
        validateString(errorMsg, parameter, name);
        if(parameter.length() < minLength){
            errorMsg.add(name + "must contain min "+minLength+" max"+maxLength);
        }else if (parameter.length() > maxLength){
            errorMsg.add(name + "must contain min "+minLength+" max"+maxLength);
        }
    }
}
