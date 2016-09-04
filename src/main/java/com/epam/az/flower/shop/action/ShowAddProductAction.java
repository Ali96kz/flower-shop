package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.service.GrowingConditionService;
import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.VisualParametersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddProductAction implements Action {
    ProductService productService = new ProductService();
    OriginService originService = new OriginService();
    VisualParametersService visualParametersService = new VisualParametersService();
    GrowingConditionService growingConditionService = new GrowingConditionService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("origins", originService.getAllOrigin());
        req.setAttribute("visualParameters", visualParametersService.getAllVisualParameters());
        req.setAttribute("growingConditions", growingConditionService.getAllGrowingConditions());
        req.setAttribute("waterInWeeks", growingConditionService.getAllWaterInWeek());
        req.setAttribute("temperatures", growingConditionService.getAllTemperature());
        return new ActionResult("product-add");
    }
}
