package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
    static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;
    //TODO put this in property
    public void initActions() {
        actions = new HashMap<>();
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/addMoneyToBalance", new AddMoneyAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/edit/product", new ShowProductEditAction());
        actions.put("POST/add-product", new ShowProductPage());

        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/product-inf", new ShowProductPage());
        actions.put("GET/edit/product", new ShowProductEditAction());
        actions.put("GET/basket/delete/product", new DeleteProductFromBasket());
        actions.put("GET/product-in-basket", new ProductInBasketAction());
        actions.put("GET/buy-all-basket", new BuyBasketAction());
        actions.put("GET/main", new ShowPageAction("main-page"));

        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/admin", new ShowAdminPage());
        actions.put("GET/basket", new ShowBasketAction());
        actions.put("GET/registration", new ShowPageAction("registration"));
        actions.put("GET/vitrine", new ShowVitrineAction());
        actions.put("GET/profile", new ShowProfileAction());
        actions.put("GET/basket", new ShowBasketAction());
    }
    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            initActions();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }

}
