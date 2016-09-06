package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.List;

public class ProductService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProductDAO productDAO = daoFactory.getDao(ProductDAO.class);
    private FlowerService flowerService = new FlowerService();
    private OriginService originService = new OriginService();
    private GrowingConditionService growingConditionService = new GrowingConditionService();

    public void update(Product product){
        productDAO.update(product);
    }

    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            if (product != null) {
                Origin origin = originService.findById(product.getOrigin().getId());
                Flower flower = flowerService.findById(product.getFlower().getId());
                product.setFlower(flower);
                product.setOrigin(origin);
            }
        }
        return products;
    }

    public ProductPagination getPagination() {
        List<Product> products = getAllProduct();
        ProductPagination pagination = new ProductPagination();
        ProductList productList = new ProductList();
        int i = 0;
        for (Product product : products) {
            if (product != null) {
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
        }

        pagination.addProducts(productList);
        return pagination;
    }

    public int addNewProduct(Product product) {
        GrowingCondition growingCondition = product.getFlower().getGrowingCondition();
        if (growingCondition.getId() == null) {
            int growinId = growingConditionService.add(growingCondition);
            growingCondition.setId(growinId);
        }

        int flowerId = flowerService.insert(product.getFlower());
        product.getFlower().setId(flowerId);
        int id = productDAO.insert(product);
        return id;
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
}
