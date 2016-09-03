package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.List;

public class ProductService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureDAO temperatureDAO = new TemperatureDAO();
    private WaterInWeekDAO waterInWeekDAO = new WaterInWeekDAO();
    private ProductDAO productDAO = daoFactory.getDao(ProductDAO.class);
    private OriginDAO originDAO = daoFactory.getDao(OriginDAO.class);
    private FlowerDAO flowerDAO = daoFactory.getDao(FlowerDAO.class);
    private VisualParametersDAO visualParametersDAO = daoFactory.getDao(VisualParametersDAO.class);
    public List<Origin> getAllOrigin(){
        return originDAO.getAll();
    }

    public List<VisualParameters> getAllVisualParameters(){
        return visualParametersDAO.getAll();
    }
    public List<Flower> getAllFlowers(){
        return flowerDAO.getAll();
    }
    public List<Temperature> getAllTemperature(){
        return temperatureDAO.getAll();
    }
    public List<WaterInWeek> getAllWaterInWeek(){
        return waterInWeekDAO.getAll();
    }

    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAll();
        return products;
    }

    public ProductPagination getPagination() {
        List<Product> products = getAllProduct();
        ProductPagination pagination = new ProductPagination();
        ProductList productList = new ProductList();
        int i = 0;
        for (Product product : products) {
            if ((i + 1) % 10 == 0) {
                productList.add(product);
                pagination.addProducts(productList);
                productList = new ProductList();
                i++;
            }
            if (product.getDeleteDay() == null) {
                i++;
                productList.add(product);
            }
        }

        pagination.addProducts(productList);
        return pagination;
    }

    public int addNewProduct(Product product) {
        int flowerId = flowerDAO.insert(product.getFlower());
        product.getFlower().setId(flowerId);
        int id = productDAO.insert(product);
        return id;
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}
