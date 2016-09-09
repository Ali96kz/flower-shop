package com.epam.az.flower.shop.action;

public class ActionResult {
    private String view;
    private boolean redirect = false;

    public ActionResult(String page, boolean redirect) {
        this.view = page;
        this.redirect = redirect;
    }

    public ActionResult(String page) {
        this.view = page;
    }

    public String getView() {
        return view;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
