package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action {
    private String url;
    public ShowPageAction(String url){
        this.url = url;
    }
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionResult actionResult = new ActionResult(url);
        return actionResult;
    }
}
