package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.List;

public class ProductService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProductDAO productDAO = daoFactory.getDao(ProductDAO.class);
    private FlowerService flowerService = new FlowerService();
    private OriginService originService = new OriginService();
    public void update(Product product){
        flowerService.update(product.getFlower());
        productDAO.update(product);
    }

    public List<Product> getAllProduct() throws ServiceException {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            fillProduct(product);
        }
        return products;
    }
    public void getPaginatedProduct() throws ServiceException {
        List<Product> products = getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getDeleteDay() != null){
                products.remove(i);
            }
        }

    }
    public int addNewProduct(Product product) throws ServiceException {
        int flowerId = flowerService.insert(product.getFlower());
        Flower flower = flowerService.findById(flowerId);
        flower.setId(flowerId);
        product.setFlower(flower);
        int id = productDAO.insert(product);
        return id;
    }

    public Product findById(int id) throws ServiceException {
        Product product = null;
        try {
            product = productDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't get product by id", e);
        }
        fillProduct(product);
        return product;
    }

    public void fillProduct(Product product) throws ServiceException {
        if (product != null) {
            Origin origin = originService.findById(product.getOrigin().getId());
            Flower flower = flowerService.findById(product.getFlower().getId());
            product.setFlower(flower);
            product.setOrigin(origin);
        }

    }
    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
}
