package com.epam.az.flower.shop.action.product;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.validator.AddProductValidator;
import com.epam.az.flower.shop.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddProductAction implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService = new ProductService();
    Validator validator = new AddProductValidator();
    ShowAddProductPageAction showAddProductPageAction = new ShowAddProductPageAction();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Product product = new Product();
        Flower flower = new Flower();
        Origin origin = new Origin();
        VisualParameters visualParameters = new VisualParameters();
        GrowingCondition growingCondition = new GrowingCondition();
        List<String> errorMsg = validator.isValidate(req);

        if (errorMsg.size() > 0) {
            showAddProductPageAction.execute(req, resp);
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("product-add");
        }

        if (req.getParameter("growingConditionName") != null) {
            String name = req.getParameter("growingConditionName");
            int temperatureId = stringAdapter.toInt(req.getParameter("temperatureId"));
            int waterInWeekId = stringAdapter.toInt(req.getParameter("waterInWeekId"));

            Temperature temperature = new Temperature();
            temperature.setId(temperatureId);
            WaterInWeek waterInWeek = new WaterInWeek();
            waterInWeek.setId(waterInWeekId);

            growingCondition.setName(name);
            growingCondition.setTemperature(temperature);
            growingCondition.setWaterInWeek(waterInWeek);
        } else {
            growingCondition.setId(stringAdapter.toInt(req.getParameter("growingConditionId")));
        }

        int originId = stringAdapter.toInt(req.getParameter("originId"));
        int visualParametersId = stringAdapter.toInt(req.getParameter("visualParametersId"));

        origin.setId(originId);
        visualParameters.setId(visualParametersId);
        flower.setGrowingCondition(growingCondition);
        flower.setName(req.getParameter("name"));
        flower.setAverageHeight(stringAdapter.toInt(req.getParameter("averageHeight")));
        flower.setVisualParameters(visualParameters);
        flower.setGrowingCondition(growingCondition);

        product.setOrigin(origin);
        product.setFlower(flower);
        product.setDescription(req.getParameter("description"));
        product.setPrice(stringAdapter.toInt(req.getParameter("price")));

        int productId = productService.addNewProduct(product);
        return new ActionResult("product-inf?id="+productId, true);
    }

}
