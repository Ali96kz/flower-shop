package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowManagePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ShowVitrineAction showVitrineAction = new ShowVitrineAction();
        showVitrineAction.execute(req, resp);

        return new ActionResult("manager");
    }
}
