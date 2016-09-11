package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
    static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;
    private Properties properties;

    public void initActions() {
        actions = new HashMap<>();
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/addMoneyToBalance", new AddMoneyAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/add-product", new AddProductAction());
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/admin-registration", new AdminAddUserAction());
        actions.put("POST/edit-product", new EditProductAction());
        actions.put("POST/edit-user", new EditUserAction());

        actions.put("GET/profile", new ShowProfileAction());
        actions.put("GET/edit-user", new ShowUserEditPage());
        actions.put("GET/delete-product", new DeleteProductAction());
        actions.put("GET/edit-product", new ShowProductEditPageAction());
        actions.put("GET/vitrine", new ShowOnlineVitrineAction());
        actions.put("GET/manager", new ShowManagerPageAction());
        actions.put("GET/add-product", new ShowAddProductPageAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/transaction", new ShowTransactionAction());
        actions.put("GET/main", new ShowPageAction("main-page"));
        actions.put("GET/registration", new ShowPageAction("registration"));
        actions.put("GET/admin-registration", new ShowAdminRegisterNewPAge());
        actions.put("GET/product-inf", new ShowProductPage());
        actions.put("GET/product-in-basket", new ProductInBasketAction());
        actions.put("GET/cash", new ShowCash());
        actions.put("GET/deleteProduct", new DeleteProductFromBasket());
        actions.put("GET/delete-profile", new ShowPageAction("delete-profile"));
        actions.put("GET/buy-all-basket", new BuyBasketAction());
        actions.put("GET/buy-product", new BuyProductAction());
        actions.put("GET/delete-user", new DeleteUserAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/admin", new ShowAdminPage());
        actions.put("GET/basket", new ShowBasketAction());
    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            initActions();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }

}
