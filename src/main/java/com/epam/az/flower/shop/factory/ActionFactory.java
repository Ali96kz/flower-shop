package com.epam.az.flower.shop.factory;

import com.epam.az.flower.shop.Main;
import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.action.LoginAction;
import com.epam.az.flower.shop.action.RegisterAction;

import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions ;
    public void initActions(){
        actions = new HashMap<>();
        actions.put("POST/register", new LoginAction());
        actions.put("POST/register", new RegisterAction());
    }
    public Action getAction(HttpServletRequest request){
        if(actions == null){
            initActions();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
