package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.DeleteProductFromBasket;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DeleteProductFromBasketTest {
    public static final String JSP_PAGE_NAME_BASKET = "basket";
    public static final String PRODUCT_ID = "id";
    private TestHttpResponse response = new TestHttpResponse();
    private TestHttpRequest request = new TestHttpRequest();
    private TestSession session = new TestSession();
    private Basket basket = new Basket();
    private ProductService productService = new ProductService();
    private DeleteProductFromBasket deleteProductFromBasket = new DeleteProductFromBasket();

    @Before
    public void init() throws ServiceException {
        basket.add(productService.findById(1));
        basket.add(productService.findById(2));
        basket.add(productService.findById(3));
        basket.add(productService.findById(4));

        session.setAttribute("basket", basket);
        request.setParameter(PRODUCT_ID, "1");
        request.setHttpSession(session);
    }

    @Test
    public void testWithNormalParameter() throws ActionException {
        ActionResult actionResult = deleteProductFromBasket.execute(request, response);
        assertEquals(basket.getProducts().size(), 3);
        assertEquals(actionResult.getView(), JSP_PAGE_NAME_BASKET);
    }

    @Test
    public void testWithIncorrectProductId() throws ActionException {
        request.setParameter(PRODUCT_ID, "45.6");
        ActionResult actionResult = deleteProductFromBasket.execute(request, response);
        assertEquals(basket.getProducts().size(), 4);
        assertEquals(actionResult.getView(), JSP_PAGE_NAME_BASKET);
    }

    @Test
    public void testWithUnexitInBasketProduct() throws ActionException {
        request.setParameter(PRODUCT_ID, "45");
        ActionResult actionResult = deleteProductFromBasket.execute(request, response);
        assertEquals(basket.getProducts().size(), 4);
        assertEquals(actionResult.getView(), JSP_PAGE_NAME_BASKET);
    }
}
