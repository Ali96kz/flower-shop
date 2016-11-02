package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Validator {
    String YOU_DIDN_T_INSERT = "You didn't insert ";
    String PLEASE_INSERT = "please insert ";
    String MUST_CONTAIN_MIN = " must contain min ";
    String MAX = " max ";
    String INCORRECT = "incorrect, ";
    String CAN_T_CONTAIN_A_NUMBER = " can't contain a number";
    String CAN_T_CONTAIN_JUST_WHITE_SPACE = " can't contain just white space";
    String NUMBER_REGEX = "[0-9]+";
    String FIELD_MENU_NAME_MONEY = "money";

    String FIELD_MENU_FLOWER_NAME = "flower name";
    String FIELD_MENU_DESCRIPTION = "description";
    String FIELD_MENU_AVERAGE_HEIGHT = "average height";
    String FIELD_MENU_PRICE = "price";
    String PARAMETER_MONEY = "money";

    String PARAMETER_NAME_PRICE = "price";
    String PARAMETER_NAME_DESCRIPTION = "description";
    String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    String SESSION_PARAMETER_USER_ID = "userId";
    String SESSION_PARAMETER_BASKET_OBJECT = "basket";
    String PARAMETER_USER_ID = "userId";
    String PARAMETER_PRODUCT_ID = "productId";
    String ATTRIBUTE_NAME_BASKET = "basket";

    String COULDN_T_BE_BELOW_ZERO = "error.template.number.below.zero.error";
    String NAME_MUST_CONTAIN_ERROR = " must contain just name must contain A-Z,a-z, white space";
    String ADD_PRODUCT_INTO_BASKET_ERROR_MSG = "You must add some products in your basket";
    String EMPTY_BASKET_ERROR = "Basket is empty";
    String SIGN_IN_ERROR = "You must sign in to buy something in own shop";
    String SIGN_IN_ERROR_MSG = "You must sign in, to buy something in own shop";
    String HANVEN_T_ENOUGH_MONEY_ERROR = "You haven't enough money";

    String ERROR_PRODUCT_NAME = "product id";
    String NICK_NAME = "nick name";
    String PARAMETER_NAME_PAGE = "page";
    int NICKNAME_MAX_LENGTH = 16;
    int NICKNAME_MIN_LENGTH = 3;

    int PASSWORD_MIN_LENGTH = 6;
    int PASSWORD_MAX_LENGTH = 12;
    int DESCRIPTION_MIN_LENGTH = 16;
    int DESCRIPTION_MAX_LENGTH = 144;
    int FLOWER_NAME_MIN_LENGTH = 4;
    int FLOWER_NAME_MAX_LENGTH = 16;

    String UNEXIST_PRODUCT_ERROR = "This product don't exist in our vitrine";
    String PASSWORD_OR_NICK_NAME_INCORRECT = "login.page.error.incorrect.password";
    String INCORRECT_DATE_ERROR_MSG = "registration.page.error.incorrect.date";
    String DIFFERENT_PASSWORD_ERROR_MSG = "registration.page.error.different.password";
    String BUSY_NICKNAME_ERROR_MSG = "registration.page.error.busy.nickname";

    String MATCH_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    String PARAMETER_FIRST_NAME = "firstName";
    String PARAMETER_NICK_NAME = "nickName";
    String PARAMETER_LAST_NAME = "lastName";
    String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    String PARAMETER_PASSWORD = "password";
    String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    String ATTRIBUTE_FIRST_NAME = "first name";
    String ATTRIBUTE_NAME_LAST_NAME = "last name";
    String ATTRIBUTE_NAME_NICK_NAME = "nick name";
    String ATTRIBUTE_NAME_CONFIRM_PASSWORD = "confirm password";
    String ATTRIBUTE_NAME_PASSWORD = "password";

    List<String> isValidate(HttpServletRequest request) throws ValidatorException;

}
