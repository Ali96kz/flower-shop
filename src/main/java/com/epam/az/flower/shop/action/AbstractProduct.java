package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.service.*;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractProduct implements Action {
    protected ProductService productService;
    protected OriginService originService;
    protected VisualParametersService visualParametersService;
    protected GrowingConditionService growingConditionService;
    protected FlowerTypeService flowerTypeService;
    protected TemperatureService temperatureService;


    StringAdapter stringAdapter = new StringAdapter();

    public AbstractProduct() throws ActionException {
        try {
            productService = new ProductService();
            originService = new OriginService();
            temperatureService = new TemperatureService();
            flowerTypeService = new FlowerTypeService();
            visualParametersService = new VisualParametersService();
            growingConditionService = new GrowingConditionService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }


    public void setValue(HttpServletRequest req) throws ActionException {
        try {
            req.setAttribute("flowerTypes", flowerTypeService.getAllFlowerType());
            req.setAttribute("origins", originService.getAll());
            req.setAttribute("visualParameters", visualParametersService.getAll());
            req.setAttribute("growingConditions", growingConditionService.getAllGrowingConditions());
            req.setAttribute("temperatures", temperatureService.getAll());
        } catch (ServiceException e) {
            throw new ActionException("can't initialize ", e);
        }
    }


    public Product getProduct(HttpServletRequest req, Product product) {
        product.setOrigin(getOrigin(req, new Origin()));
        product.setFlower(getFlower(req, new Flower()));
        product.setDescription(req.getParameter("description"));
        product.setPrice(stringAdapter.toInt(req.getParameter("price")));
        return product;
    }

    public Flower getFlower(HttpServletRequest req, Flower flower) {
        flower.setGrowingCondition(getGrowingCondition(req, new GrowingCondition()));
        flower.setName(req.getParameter("flowerName"));
        flower.setAverageHeight(stringAdapter.toInt(req.getParameter("averageHeight")));
        flower.setVisualParameters(getVisualParameters(req, new VisualParameters()));
        flower.setFlowerType(getFlowerType(req, new FlowerType()));
        return flower;
    }

    public GrowingCondition getGrowingCondition(HttpServletRequest req, GrowingCondition growingCondition) {
        growingCondition.setId(stringAdapter.toInt(req.getParameter("growingConditionId")));
        return growingCondition;
    }

    public Origin getOrigin(HttpServletRequest req, Origin origin) {
        int originId = stringAdapter.toInt(req.getParameter("originId"));
        origin.setId(originId);
        return origin;
    }

    public VisualParameters getVisualParameters(HttpServletRequest req, VisualParameters visualParameters) {
        int visualParametersId = stringAdapter.toInt(req.getParameter("visualParametersId"));
        visualParameters.setId(visualParametersId);
        return visualParameters;
    }

    public FlowerType getFlowerType(HttpServletRequest req, FlowerType flowerType) {
        int flowerTypeId = stringAdapter.toInt(req.getParameter("flowerTypeId"));
        flowerType.setId(flowerTypeId);
        return flowerType;
    }
}
