package com.epam.az.flower.shop.action.vitrine;

import com.epam.az.flower.shop.action.AbstractVitrine;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AverageHeightParamter extends AbstractVitrine{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setPaginationList(req);
        setPage(req);
        return new ActionResult("vitrin-average-height");
    }
}
