package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.ProductDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.ProductList;
import com.epam.az.flower.shop.entity.ProductPagination;

import java.util.List;

public class ProductService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    ProductDAO productDAO = daoFactory.getDao(ProductDAO.class);

    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAll();
        return products;
    }

    public ProductPagination getPagination() {
        List<Product> products = getAllProduct();
        ProductPagination pagination = new ProductPagination();
        ProductList productList = new ProductList();
        for (int i = 0; i < products.size(); i++) {
            if ((i + 1) % 10 == 0) {
                productList.add(products.get(i));
                pagination.addProducts(productList);
                productList = new ProductList();
            }
            productList.add(products.get(i));
        }

        pagination.addProducts(productList);
        return pagination;
    }

    public void addNewProduct(Product product) {

    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}
