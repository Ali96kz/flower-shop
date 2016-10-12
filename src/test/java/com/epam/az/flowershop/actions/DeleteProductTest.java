package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.DeleteProductAction;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import org.junit.Test;

import java.util.List;

public class DeleteProductTest {
    private TestHttpResponse response = new TestHttpResponse();
    private TestHttpRequest request = new TestHttpRequest();
    private DeleteProductAction deleteProductAction = new DeleteProductAction();
    private ProductService productService = new ProductService();
    public DeleteProductTest() throws ActionException, ServiceException {
    }

    @Test
    public void testDeleteCorrectId() throws ActionException, ServiceException {

    }


}
