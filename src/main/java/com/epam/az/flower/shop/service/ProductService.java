package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProductDAO productDAO;
    private FlowerService flowerService;
    private OriginService originService;
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
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
            daoFactory.startTransaction(productDAO);
            productDAO.update(product);
            flowerService.update(product.getFlower());
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
            daoFactory.startOperation(productDAO);
            List<Product> products = productDAO.getAll();
            logger.info("get {} products from data",products.size());
            for (Product product : products) {
                fillProduct(product);
            }
            daoFactory.endOperation(productDAO);
            return products;
        } catch (DAOException e) {
            throw new ServiceException("can't execute", e);
        }
    }


    public int addNewProduct(Product product) throws ServiceException {
        try {
            int id;
            int flowerId = flowerService.insert(product.getFlower());
            Flower flower = flowerService.findById(flowerId);
            flower.setId(flowerId);
            product.setFlower(flower);
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
        Product product;
        try {
            daoFactory.startOperation(productDAO);
            product = productDAO.findById(id);
            fillProduct(product);
            daoFactory.endOperation(productDAO);

        } catch (DAOException e) {
            daoFactory.endOperation(productDAO);
            throw new ServiceException("can't get product by id", e);
        }
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
