package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    int PAGE_SIZE = 12;

    String ATTRIBUTE_NAME_ERROR_MSG = "errorMsg";
    String JSP_PAGE_NAME_EDIT_PRODUCT = "product-edit";
    String JSP_PAGE_NAME_PRODUCT = "product-inf";
    String ATTRIBUTE_NAME_PRODUCT_ID = "?productId=";
    String ATTRIBUTE_NAME_USERS = "users";

    String JSP_PAGE_NAME_CASH = "cash";
    String JSP_PAGE_NAME_BASKET = "basket";
    String JSP_PAGE_NAME_MANAGER = "manager";
    String JSP_PAGE_NAME_VITRINE = "vitrine";
    String JSP_PAGE_NAME_PRODUCT_EDIT = "product-edit";
    String JSP_PAGE_PRODUCT_INF = "product-inf?productId=";
    String JSP_PAGE_NAME_ADMIN = "admin";
    String JSP_PAGE_ADMIN_REGISTRATION = "admin-registration";
    String JSP_PAGE_NAME_DELETE_PROFILE = "delete-profile";
    String JSP_PAGE_NAME_LOGIN = "login";
    String JSP_PAGE_NAME_PRODUCT_ADD = "product-add";
    String JSP_PAGE_NAME_BILL = "bill";
    String JSP_PAGE_NAME_PRODUCT_INF = "product-inf";
    String JSP_PAGE_NAME_PROFILE = "profile";
    String JSP_PAGE_NAME_TRANSACTION = "transaction";
    String JSP_PAGE_NAME_REGISTRATION = "registration";
    String JSP_PAGE_NAME_EDIT_USER = "edit-user";

    String REQUEST_ATTRIBUTE_NAME_PRODUCT_ID = "productId";
    String REQUEST_ATTRIBUTE_NAME_PRODUCT = "product";
    String REQUEST_ATTRIBUTE_PAGE_LIST = "pageList";
    String PARAMETER_NAME_ORIGIN_ID = "originId";
    String PARAMETER_NAME_VISUAL_PARAMETERS_ID = "visualParametersId";
    String PARAMETER_NAME_FLOWER_TYPE_ID = "flowerTypeId";
    String PARAMETER_NAME_GROWING_CONDITION_ID = "growingConditionId";
    String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    String PARAMETER_NAME_DESCRIPTION = "description";
    String PARAMETER_NAME_PRICE = "price";
    String PARAMETER_NAME_ORIGINS = "origins";
    String PARAMETER_NAME_VISUAL_PARAMETERS = "visualParameters";
    String PARAMETER_NAME_GROWING_CONDITIONS = "growingConditions";
    String PARAMETER_NAME_FLOWER_TYPES = "flowerTypes";
    String PARAMETER_NAME_TEMPERATURES = "temperatures";
    String ATTRIBUTE_NAME_USER_ROLE_ID = "userRoleId";
    String ATTRIBUTE_PRODUCTS = "products";
    String PARAMETER_NAME_PAGE = "page";
    String PARAMETER_USER_ID = "id";
    String ATTRIBUTE_NAME_SUM = "sum";
    String ATTRIBUTE_ERROR_MSG = "errorMsg";
    String ATTRIBUTE_NAME_PRICE = "price";
    String PARAMETER_NICK_NAME = "nickName";
    String PARAMETER_PASSWORD = "password";
    String PARAMETER_NAME_MONEY = "money";
    String PARAMETER_FIRST_NAME = "firstName";
    String PARAMETER_LAST_NAME = "lastName";
    String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    String PARAMETER_PRODUCT_ID = "productId";
    String ROLE_CUSTOMER = "customer";
    String ATTRIBUTE_USER_ROLES = "userRoles";
    String ATTRIBUTE_BASKET = "basket";
    String ATTRIBUTE_NAME_BILL = "bill";
    String ATTRIBUTE_NAME_PRODUCT = "product";
    String ATTRIBUTE_NAME_TRANSACTION_LIST = "transactions";
    String ATTRIBUTE_NAME_ORIGIN_LIST = "origins";
    String ATTRIBUTE_NAME_USER = "user";
    String SESSION_PARAMETER_USER_ID = "userId";
    String PAGE_DELETE_PROFILE = "delete-profile";

    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException;
}
