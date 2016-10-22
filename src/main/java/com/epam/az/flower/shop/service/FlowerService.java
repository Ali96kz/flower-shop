package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.VisualParameters;

public class FlowerService {
    private static final Class<FlowerDAO> daoClass = FlowerDAO.class;
    private VisualParametersService visualParametersService = new VisualParametersService();
    private GrowingConditionService growingConditionService = new GrowingConditionService();
    private ProxyService proxyService = new ProxyService(daoClass);

    public Flower findById(int id) throws ServiceException {
        Flower flower = (Flower) proxyService.findById(id);

        VisualParameters visualParameters = visualParametersService.findById(flower.getVisualParameters().getId());
        GrowingCondition growingCondition = growingConditionService.findById(flower.getGrowingCondition().getId());

        flower.setGrowingCondition(growingCondition);
        flower.setVisualParameters(visualParameters);
        return flower;
    }

    public void update(Flower flower) throws ServiceException {
        proxyService.update(flower);
    }

    public int insert(Flower flower) throws ServiceException {
        int flowerId = proxyService.insert(flower);
        return flowerId;
    }
}
