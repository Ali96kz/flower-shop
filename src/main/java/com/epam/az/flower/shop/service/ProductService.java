package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.List;

public class ProductService {
    private FlowerService flowerService = new FlowerService();
    private OriginService originService = new OriginService();

    public void update(Product product) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerService flowerService = new FlowerService();
                ProductDAO productDAO = daoFactory.createDAO(ProductDAO.class);
                flowerService.update(product.getFlower());

                daoFactory.startTransaction();
                productDAO.update(product);
                daoFactory.commitTransaction();

            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public List<Product> getAllProduct() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                ProductDAO productDAO = daoFactory.createDAO(ProductDAO.class);
                List<Product> products = productDAO.getAll();
                for (Product product : products) {
                    fillProduct(product);
                }
                return products;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
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
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerService flowerService = new FlowerService();
                ProductDAO productDAO = daoFactory.createDAO(ProductDAO.class);
                daoFactory.startTransaction();

                int flowerId = flowerService.insert(product.getFlower());
                Flower flower = flowerService.findById(flowerId);
                flower.setId(flowerId);
                product.setFlower(flower);

                int id = productDAO.insert(product);
                daoFactory.commitTransaction();
                return id;

            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public Product findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                ProductDAO productDAO = daoFactory.createDAO(ProductDAO.class);
                Product product = productDAO.findById(id);
                fillProduct(product);
                return product;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);

        }
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
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                ProductDAO productDAO = daoFactory.createDAO(ProductDAO.class);
                daoFactory.startTransaction();
                productDAO.delete(id);
                daoFactory.commitTransaction();
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);

        }
    }
}

