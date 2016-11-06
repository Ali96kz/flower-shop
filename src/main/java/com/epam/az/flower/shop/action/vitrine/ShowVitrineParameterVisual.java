package com.epam.az.flower.shop.action.vitrine;

import com.epam.az.flower.shop.action.AbstractVitrine;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowVitrineParameterVisual extends AbstractVitrine {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setPaginationList(req);
        setPage(req);
        setValue(req);
        return new ActionResult("vitrine-visual-parameter");
    }
}
