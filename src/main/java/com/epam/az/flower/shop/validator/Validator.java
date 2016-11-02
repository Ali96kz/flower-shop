package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Validator {
    int NICKNAME_MAX_LENGTH = 16;
    int NICKNAME_MIN_LENGTH = 3;

    int PASSWORD_MIN_LENGTH = 6;
    int PASSWORD_MAX_LENGTH = 12;
    int DESCRIPTION_MIN_LENGTH = 16;
    int DESCRIPTION_MAX_LENGTH = 144;
    int FLOWER_NAME_MIN_LENGTH = 4;
    int FLOWER_NAME_MAX_LENGTH = 16;

    String NUMBER_REGEX = "[0-9]+";
    String ERROR_MSG_CASH_INCORRECT_MONEY = "error.cash.incorrect.money";

    String PARAMETER_NAME_PRICE = "price";
    String PARAMETER_NAME_DESCRIPTION = "description";
    String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    String PARAMETER_NAME_PAGE = "page";
    String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    String SESSION_PARAMETER_USER_ID = "userId";
    String SESSION_PARAMETER_BASKET_OBJECT = "basket";
    String PARAMETER_USER_ID = "userId";

    String PARAMETER_PRODUCT_ID = "productId";
    String ATTRIBUTE_NAME_BASKET = "basket";
    String MATCH_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    String PARAMETER_FIRST_NAME = "firstName";
    String PARAMETER_NICK_NAME = "nickName";
    String PARAMETER_LAST_NAME = "lastName";
    String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    String PARAMETER_PASSWORD = "password";

    String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    String FIELD_MENU_FLOWER_NAME = "error.add.product.flower.name";
    String FIELD_MENU_DESCRIPTION = "error.add.description";
    String FIELD_MENU_AVERAGE_HEIGHT = "error.add.product.average.height";
    String FIELD_MENU_PRICE = "error.add.product.price";

    String PARAMETER_MONEY = "money";
    String ADD_PRODUCT_INTO_BASKET_ERROR_MSG = "error.basket.empty";
    String EMPTY_BASKET_ERROR = "error.basket.empty";
    String SIGN_IN_ERROR = "error.buy.sign.in";
    String HAVEN_T_ENOUGH_MONEY_ERROR = "error.havent.enough.money";

    String UNEXIST_PRODUCT_ERROR = "error.show.product.incorrect.number";

    String ERROR_USER_NOT_FOUND = "login.error.notFound.user";
    String ERROR_INCORRECT_PASSWORD = "login.error.incorrect.password";

    String ERROR_INCORRECT_NICK_NAME = "login.error.incorrect.nickname";
    String ERROR_INCORRECT_ID_PRODUCT = "error.basket.delete.incorrect.number";

    String INCORRECT_DATE_ERROR_MSG = "registration.page.error.incorrect.date";
    String DIFFERENT_PASSWORD_ERROR_MSG = "registration.page.error.different.password";
    String BUSY_NICKNAME_ERROR_MSG = "registration.page.error.busy.nickname";
    String ERROR_ATTRIBUTE_FIRST_NAME = "user.profile.first.name";
    String ERROR_ATTRIBUTE_NAME_LAST_NAME = "user.profile.last.name";
    String ERROR_ATTRIBUTE_NAME_NICK_NAME = "user.nick.name";
    String ERROR_ATTRIBUTE_NAME_CONFIRM_PASSWORD = "registration.page.error.incorrect.confirm.password";
    String ERROR_ATTRIBUTE_NAME_PASSWORD = "registration.page.error.incorrect.password";

    List<String> isValidate(HttpServletRequest request) throws ValidatorException;
}
