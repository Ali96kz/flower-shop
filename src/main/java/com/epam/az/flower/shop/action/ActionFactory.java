package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.epam.az.flower.shop.action.product.*;
import com.epam.az.flower.shop.util.PropertyWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
    static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public void initActions() {
        actions = new HashMap<>();
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/addMoneyToBalance", new AddMoneyAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/edit-product", new EditProductAction());
        actions.put("POST/add-product", new AddProductAction());
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/admin-registration", new AdminAddUserAction());

        actions.put("GET/profile", new ShowProfileAction());
        actions.put("GET/delete-product", new DeleteProductAction());
        actions.put("GET/edit-product", new ShowProductEditAction());
        actions.put("GET/vitrine", new ShowVitrineAction());
        actions.put("GET/manager", new ShowManagePageAction());
        actions.put("GET/add-product", new ShowAddProductPageAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/transaction", new ShowTransactionAction());
        actions.put("GET/main", new ShowPageAction("main-page"));
        actions.put("GET/registration", new ShowPageAction("registration"));
        actions.put("GET/admin-registration", new ShowAdminRegisterNewPAge());
        actions.put("GET/product-inf", new ShowProductPage());
        actions.put("GET/product-in-basket", new ProductInBasketAction());
        actions.put("GET/edit-product", new ShowProductEditAction());
        actions.put("GET/cash", new ShowCash());
        actions.put("GET/deleteProduct", new DeleteProductFromBasket());
        actions.put("GET/buy-all-basket", new BuyBasketAction());
        actions.put("GET/buy-product", new BuyProductAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/admin", new ShowAdminPage());
    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            initActions();
        }
        String s = request.getMethod() + request.getPathInfo();
        if (s.equalsIgnoreCase("GET/basket")) {
            PropertyWorker propertyWorker = new PropertyWorker();
            Properties properties= propertyWorker.readProperty("./src/main/resources/actionClass");
            try {
                System.out.println(properties);
                ShowBasketAction object = (ShowBasketAction) Class.forName(properties.getProperty("GET/basket")).newInstance();
                return object;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }

}
