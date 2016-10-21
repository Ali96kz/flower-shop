package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ShowOnlineVitrineAction;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ShowOnlineVitrineTest {
    private TestHttpRequest request = new TestHttpRequest();
    private ShowOnlineVitrineAction showOnlineVitrineAction = new ShowOnlineVitrineAction();
    private TestHttpResponse response = new TestHttpResponse();

    @Test
    public void testProductPageNotEmpty() throws ActionException {
        for (int i = 0; i < 4; i++) {
            request.setParameter("page", String.valueOf(i));
            showOnlineVitrineAction.execute(request, response);
            List<Product> products = (List<Product>) request.getAttribute("products");
            assertTrue(products.size() > 0);
        }
    }

    @Test
    public void testWithIncorrectPageNumber() throws ActionException {
        for (int i = -5; i < 10; i++) {
            request.setParameter("page", String.valueOf(i));
            showOnlineVitrineAction.execute(request, response);
            List<Product> products = (List<Product>) request.getAttribute("products");
            assertTrue(products.size() > 0);
        }
    }
}
