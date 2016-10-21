package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.ProductDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductService {
    public static final Class<ProductDAO> PRODUCT_DAO_CLASS = ProductDAO.class;
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
    private FlowerService flowerService = new FlowerService();
    private OriginService originService = new OriginService();
    private ProxyService proxyService = new ProxyService(PRODUCT_DAO_CLASS);

    public void update(Product product) throws ServiceException {
        flowerService.update(product.getFlower());
        proxyService.update(product);
    }

    public List<Product> getAllNotDeleteProduct() throws ServiceException {
        List<Product> products = proxyService.getAll();
        logger.info("get {} products from data", products.size());

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getDeleteDay() == null) {
                fillProduct(products.get(i));
            }
        }
        return products;
    }


    public int addNewProduct(Product product) throws ServiceException {
        int flowerId = flowerService.insert(product.getFlower());

        Flower flower = flowerService.findById(flowerId);
        flower.setId(flowerId);
        product.setFlower(flower);

        return proxyService.insert(product);
    }

    public Product findById(int id) throws ServiceException {
        Product product = (Product) proxyService.findById(id);
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

    public boolean isExist(int id) throws ServiceException {
        Product product = (Product) proxyService.findById(id);
        if (product.getFlower() == null) {
            return false;
        }
        return true;
    }

    public void deleteProduct(int id) throws ServiceException {
        proxyService.delete(id);
    }
}
