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

    public ProductPagination getPagination() throws ServiceException {
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
