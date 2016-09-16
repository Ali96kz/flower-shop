package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.List;

public class ProductService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProductDAO productDAO;
    private FlowerService flowerService;
    private OriginService originService;

    public ProductService() throws ServiceException {
        try {
            productDAO = daoFactory.getDao(ProductDAO.class);

            flowerService = new FlowerService();
            originService = new OriginService();
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao class", e);
        }
    }

    public void update(Product product) throws ServiceException {
        try {
            flowerService.update(product.getFlower());
            daoFactory.startTransaction(productDAO);
            productDAO.update(product);
            daoFactory.commitTransaction(productDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(productDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll ack transaction", e);
            }
            throw new ServiceException("can't initialize dao class", e);
        }
    }

    public List<Product> getAllProduct() throws ServiceException {
        try {
            daoFactory.startTransaction(productDAO);
            List<Product> products = productDAO.getAll();
            for (Product product : products) {
                fillProduct(product);
            }
            daoFactory.commitTransaction(productDAO);
            return products;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(productDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't execute", e);
        }

    }

    public void getPaginatedProduct() throws ServiceException {
        List<Product> products = getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getDeleteDay() != null) {
                products.remove(i);
            }
        }

    }

    public int addNewProduct(Product product) throws ServiceException {
        try {
            int flowerId = flowerService.insert(product.getFlower());
            Flower flower = flowerService.findById(flowerId);
            flower.setId(flowerId);
            product.setFlower(flower);
            int id = 0;
            daoFactory.startTransaction(productDAO);
            id = productDAO.insert(product);
            daoFactory.commitTransaction(productDAO);
            return id;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(productDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't add product", e);
        }
    }

    public Product findById(int id) throws ServiceException {
        Product product = null;
        try {
            product = productDAO.findById(id);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(productDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
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

    public void deleteProduct(int id) throws ServiceException {
        try {
            daoFactory.startTransaction(productDAO);
            productDAO.delete(id);
            daoFactory.commitTransaction(productDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(productDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't initialize class", e);
        }
    }
}
