package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.BuyBasketAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.TransactionService;
import com.epam.az.flower.shop.service.UserTransactionService;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BuyBasketTest {
    public static final int TEST_USER_ID = 1;
    public static final String TRANSACTION_NAME_BUY_PRODUCT = "buy product";

    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private Basket basket = new Basket();
    private ProductService productService = new ProductService();
    private BuyBasketAction buyBasketAction = new BuyBasketAction();
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO = new UserDAO();
    private UserTransactionService transactionService = new UserTransactionService();

    public BuyBasketTest() throws ServiceException, ActionException {
    }

    @Before
    public void init() throws ServiceException {
        basket.add(productService.findById(1));
        basket.add(productService.findById(2));
        basket.add(productService.findById(3));
        basket.add(productService.findById(4));

        session.setAttribute("userId", TEST_USER_ID);
        session.setAttribute("basket", basket);

        request.setHttpSession(session);
    }

    @Test
    public void testBasketWithSomeProducts() throws ActionException, SQLException, DAOException, ServiceException {
        userDAO.setConnection(connectionPool.getConnection());
        User beforeUpdateUser = userDAO.findById(TEST_USER_ID);
        userDAO.getConnection().close();

        ActionResult actionResult = buyBasketAction.execute(request, response);
        assertEquals(actionResult.getView(), "bill");

        testUserBalance(beforeUpdateUser);
        testTransaction();
    }

    public void testUserBalance(User beforeUpdateUser) throws DAOException, SQLException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(TEST_USER_ID);

        assertEquals(beforeUpdateUser.getBalance() - basket.getSum(), user.getBalance());
    }

    public void testTransaction() throws ServiceException {
        List<UserTransaction> transactions = transactionService.getAll(TEST_USER_ID);
        List<Product> products = basket.getProducts();
        for (int i = transactions.size() - 1, j = 1;i >=  0; i--, j++) {
            if(j < 4) {
                assertEquals(transactions.get(i).getSum(), products.get(j).getPrice());
                assertEquals(transactions.get(i).getTransaction().getName(), TRANSACTION_NAME_BUY_PRODUCT);
            }
            break;
        }
    }


}