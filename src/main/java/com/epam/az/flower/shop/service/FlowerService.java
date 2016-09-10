package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.VisualParameters;

import java.util.List;

public class FlowerService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerDAO flowerDAO = daoFactory.getDao(FlowerDAO.class);
    private VisualParametersService visualParametersService = new VisualParametersService();
    private GrowingConditionService growingConditionService = new GrowingConditionService();

    public List<Flower> getAllFlowers(){
        return flowerDAO.getAll();
    }

    public Flower findById(int id){
        Flower flower = flowerDAO.findById(id);
        VisualParameters visualParameters = visualParametersService.findById(flower.getVisualParameters().getId());
        GrowingCondition growingCondition = growingConditionService.findById(flower.getGrowingCondition().getId());
        flower.setGrowingCondition(growingCondition);
        flower.setVisualParameters(visualParameters);
        return flowerDAO.findById(id);
    }
    public void update(Flower flower){
        flowerDAO.update(flower);
    }
    public int insert(Flower flower){
        return flowerDAO.insert(flower);
    }
}
