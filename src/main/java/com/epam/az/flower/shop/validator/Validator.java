package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Validator {
    public static final String YOU_DIDN_T_INSERT = "You didn't insert ";
    public static final String COULDN_T_BE_BELOW_ZERO = "couldn't be <= 0";
    public static final String PLEASE_INSERT = "please insert ";
    public static final String MUST_CONTAIN_MIN = " must contain min ";
    public static final String MAX = " max ";
    public static final String NAME_MUST_CONTAIN = " must contain just name must contain A-Z,a-z, white space";
    public static final String INCORRECT = "incorrect, ";
    public static final String CAN_T_CONTAIN_A_NUMBER = " can't contain a number";
    public static final String CAN_T_CONTAIN_JUST_WHITE_SPACE = " can't contain just white space";
    static final String NUMBER_REGEX = "[0-9]+";
    static final String PARAMETER_MONEY = "money";
    static final String FIELD_MENU_NAME_MONEY = "money";

     int DESCRIPTION_MIN_LENGTH = 16;
     int DESCRIPTION_MAX_LENGTH = 144;
     int FLOWER_NAME_MIN_LENGTH = 4;
     int FLOWER_NAME_MAX_LENGTH = 16;
     String FIELD_MENU_FLOWER_NAME = "flower name";
     String FIELD_MENU_DESCRIPTION = "description";
     String FIELD_MENU_AVERAGE_HEIGHT = "average height";
     String FIELD_MENU_PRICE = "price";
     String PARAMETER_NAME_PRICE = "price";
     String PARAMETER_NAME_DESCRIPTION = "description";
     String PARAMETER_NAME_FLOWER_NAME = "flowerName";
     String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    public static final String SIGN_IN_ERROR = "You must sign in to buy something in own shop";
    public static final String ADD_PRODUCT_INTO_BASKET_ERROR_MSG = "You must add some products in your basket";
    public static final String EMPTY_BASKET_ERROR = "Basket is empty";
    public static final String HAVENT_ENOUGH_MONEY_ERROR = "you haven't enough money";
     String SESSION_PARAMETER_USER_ID = "userId";
     String SESSION_PARAMETER_BASKET_OBJECT = "basket";
    public static final String SIGN_IN_ERROR_MSG = "You must sign in, to buy something in own shop";
    public static final String UNEXIST_PRODUCT_ERROR = "This product don't exist in our vitrine";
    public static final String HANVEN_T_ENOUGH_MONEY = "You haven't enough money";
     String PARAMETER_USER_ID = "userId";
     String PARAMETER_PRODUCT_ID = "productId";
     String PRODUCT_ID = "id";
     String ATTRIBUTE_NAME_BASKET = "basket";
     String ERROR_PRODUCT_NAME = "product id";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String NICK_NAME = "nick name";
    public static final String PASSWORD_OR_NICK_NAME_INCORRECT = "password or nick name incorrect";
     int NICKNAME_MAX_LENGTH = 16;
     int NICKNAME_MIN_LENGTH = 3;
     int PASSWORD_MIN_LENGTH = 6;
     int PASSWORD_MAX_LENGTH = 12;
     String PARAMETER_NICK_NAME = "nickName";
     String PARAMETER_NAME_PAGE = "page";

    List<String> isValidate(HttpServletRequest request) throws ValidatorException;

}
