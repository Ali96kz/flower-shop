package com.epam.az.flower.shop.factory;

import com.epam.az.flower.shop.action.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
    private Map<String, Action> actions;
    static final Logger log = LoggerFactory.getLogger(String.valueOf(RegisterAction.class));

    public void initActions() {
        actions = new HashMap<>();
        actions.put("POST/registration", new RegisterAction());
        actions.put("POST/addMoneyToBalance", new AddMoneyAction());

        actions.put("GET/registration", new ShowPageAction("registration"));
        actions.put("GET/cash", new ShowCash());
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
