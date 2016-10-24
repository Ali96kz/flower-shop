package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.service.*;
import com.epam.az.flower.shop.util.StringAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractProduct implements Action {
    private static Logger logger = LoggerFactory.getLogger(AbstractProduct.class);

    protected ProductService productService = new ProductService();
    protected OriginService originService = new OriginService();
    protected VisualParametersService visualParametersService = new VisualParametersService();
    protected GrowingConditionService growingConditionService = new GrowingConditionService();
    protected FlowerTypeService flowerTypeService = new FlowerTypeService();
    protected TemperatureService temperatureService = new TemperatureService();
    protected StringAdapter stringAdapter = new StringAdapter();

    public void setProduct(HttpServletRequest request) throws ActionException {
        try {
            int id = stringAdapter.toInt(request.getParameter(REQUEST_ATTRIBUTE_NAME_PRODUCT_ID));
            Product product = productService.findById(id);
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_PRODUCT, product);
        } catch (ServiceException e) {
            logger.error("get product from service", e);
            throw new ActionException("can't get product by id from service", e);
        }
    }

    public void setValue(HttpServletRequest req) throws ActionException {
        try {
            req.setAttribute(PARAMETER_NAME_FLOWER_TYPES, flowerTypeService.getAllFlowerType());
            req.setAttribute(PARAMETER_NAME_ORIGINS, originService.getAll());
            req.setAttribute(PARAMETER_NAME_VISUAL_PARAMETERS, visualParametersService.getAll());
            req.setAttribute(PARAMETER_NAME_GROWING_CONDITIONS, growingConditionService.getAllGrowingConditions());
            req.setAttribute(PARAMETER_NAME_TEMPERATURES, temperatureService.getAll());
        } catch (ServiceException e) {
            logger.error("can't get entity from service", e);
            throw new ActionException("can't get entity from service", e);
        }
    }

    /**
     * fill product, information get from request
     */
    public Product getProduct(HttpServletRequest req, Product product) {
        product.setOrigin(getOrigin(req, new Origin()));
        product.setFlower(getFlower(req, new Flower()));
        product.setDescription(req.getParameter(PARAMETER_NAME_DESCRIPTION));
        product.setPrice(stringAdapter.toInt(req.getParameter(PARAMETER_NAME_PRICE)));
        return product;
    }

    public Flower getFlower(HttpServletRequest req, Flower flower) {
        flower.setGrowingCondition(getGrowingCondition(req, new GrowingCondition()));
        flower.setName(req.getParameter(PARAMETER_NAME_FLOWER_NAME));
        flower.setAverageHeight(stringAdapter.toInt(req.getParameter(PARAMETER_NAME_AVERAGE_HEIGHT)));
        flower.setVisualParameters(getVisualParameters(req, new VisualParameters()));
        flower.setFlowerType(getFlowerType(req, new FlowerType()));
        return flower;
    }

    public GrowingCondition getGrowingCondition(HttpServletRequest req, GrowingCondition growingCondition) {
        growingCondition.setId(stringAdapter.toInt(req.getParameter(PARAMETER_NAME_GROWING_CONDITION_ID)));
        return growingCondition;
    }

    public Origin getOrigin(HttpServletRequest req, Origin origin) {
        int originId = stringAdapter.toInt(req.getParameter(PARAMETER_NAME_ORIGIN_ID));
        origin.setId(originId);
        return origin;
    }

    public VisualParameters getVisualParameters(HttpServletRequest req, VisualParameters visualParameters) {
        int visualParametersId = stringAdapter.toInt(req.getParameter(PARAMETER_NAME_VISUAL_PARAMETERS_ID));
        visualParameters.setId(visualParametersId);
        return visualParameters;
    }

    public FlowerType getFlowerType(HttpServletRequest req, FlowerType flowerType) {
        int flowerTypeId = stringAdapter.toInt(req.getParameter(PARAMETER_NAME_FLOWER_TYPE_ID));
        flowerType.setId(flowerTypeId);
        return flowerType;
    }
}
