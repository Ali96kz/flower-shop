package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.service.*;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractProduct implements Action {
    public static final String ATTRIBUTE_FLOWER_TYPES = "flowerTypes";
    public static final String ATTRIBUTE_ORIGINS = "origins";
    public static final String ATTRIBUTE_VISUAL_PARAMETERS = "visualParameters";
    public static final String ATTRIBUTE_GROWING_CONDITIONS = "growingConditions";
    public static final String ATTRIBUTE_TEMPERATURES = "temperatures";
    public static final String PARAMETER_DESCRIPTION = "description";
    public static final String ATTRIBUTE_PRICE = "price";
    public static final String PARAMETER_FLOWER_NAME = "flowerName";
    public static final String PARAMETER_AVERAGE_HEIGHT = "averageHeight";
    public static final String PARAMETER_GROWING_CONDITION_ID = "growingConditionId";
    public static final String PARAMETER_ORIGIN_ID = "originId";
    public static final String PARAMETER_VISUAL_PARAMETERS_ID = "visualParametersId";
    public static final String PARAMETER_FLOWER_TYPE_ID = "flowerTypeId";

    protected ProductService productService;
    protected OriginService originService;
    protected VisualParametersService visualParametersService;
    protected GrowingConditionService growingConditionService;
    protected FlowerTypeService flowerTypeService;
    protected TemperatureService temperatureService;


    StringAdapter stringAdapter = new StringAdapter();

    public AbstractProduct() {
        productService = new ProductService();
        originService = new OriginService();
        temperatureService = new TemperatureService();
        flowerTypeService = new FlowerTypeService();
        visualParametersService = new VisualParametersService();
        growingConditionService = new GrowingConditionService();

    }


    public void setValue(HttpServletRequest req) throws ActionException {
        try {
            req.setAttribute(ATTRIBUTE_FLOWER_TYPES, flowerTypeService.getAllFlowerType());
            req.setAttribute(ATTRIBUTE_ORIGINS, originService.getAll());
            req.setAttribute(ATTRIBUTE_VISUAL_PARAMETERS, visualParametersService.getAll());
            req.setAttribute(ATTRIBUTE_GROWING_CONDITIONS, growingConditionService.getAllGrowingConditions());
            req.setAttribute(ATTRIBUTE_TEMPERATURES, temperatureService.getAll());
        } catch (ServiceException e) {
            throw new ActionException("can't initialize ", e);
        }
    }


    public Product getProduct(HttpServletRequest req, Product product) {
        product.setOrigin(getOrigin(req, new Origin()));
        product.setFlower(getFlower(req, new Flower()));
        product.setDescription(req.getParameter(PARAMETER_DESCRIPTION));
        product.setPrice(stringAdapter.toInt(req.getParameter(ATTRIBUTE_PRICE)));
        return product;
    }

    public Flower getFlower(HttpServletRequest req, Flower flower) {
        flower.setGrowingCondition(getGrowingCondition(req, new GrowingCondition()));
        flower.setName(req.getParameter(PARAMETER_FLOWER_NAME));
        flower.setAverageHeight(stringAdapter.toInt(req.getParameter(PARAMETER_AVERAGE_HEIGHT)));
        flower.setVisualParameters(getVisualParameters(req, new VisualParameters()));
        flower.setFlowerType(getFlowerType(req, new FlowerType()));
        return flower;
    }

    public GrowingCondition getGrowingCondition(HttpServletRequest req, GrowingCondition growingCondition) {
        growingCondition.setId(stringAdapter.toInt(req.getParameter(PARAMETER_GROWING_CONDITION_ID)));
        return growingCondition;
    }

    public Origin getOrigin(HttpServletRequest req, Origin origin) {
        int originId = stringAdapter.toInt(req.getParameter(PARAMETER_ORIGIN_ID));
        origin.setId(originId);
        return origin;
    }

    public VisualParameters getVisualParameters(HttpServletRequest req, VisualParameters visualParameters) {
        int visualParametersId = stringAdapter.toInt(req.getParameter(PARAMETER_VISUAL_PARAMETERS_ID));
        visualParameters.setId(visualParametersId);
        return visualParameters;
    }

    public FlowerType getFlowerType(HttpServletRequest req, FlowerType flowerType) {
        int flowerTypeId = stringAdapter.toInt(req.getParameter(PARAMETER_FLOWER_TYPE_ID));
        flowerType.setId(flowerTypeId);
        return flowerType;
    }
}
