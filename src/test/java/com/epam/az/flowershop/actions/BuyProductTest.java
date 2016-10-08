package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.*;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserTransactionService;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BuyProductTest {
    public static final int TEST_USER_ID = 1;
    public static final String TRANSACTION_NAME_BUY_PRODUCT = "buy product";
    public static final String JSP_PAGE_NAME_BILL = "bill";
    public static final String TEST_PRODUCT_ID = "1";

    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private BuyProductAction buyProductAction = new BuyProductAction();
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO = new UserDAO();
    private ProductService productService = new ProductService();
    private UserTransactionService transactionService = new UserTransactionService();

    public BuyProductTest() throws ActionException, ServiceException {
    }

    @Before
    public void initRequest() throws ServiceException {
        request.setParameter("productId", TEST_PRODUCT_ID);
        session.setAttribute("userId", TEST_USER_ID);
        request.setHttpSession(session);
    }

    @Test
    public void testWithNormalValue() throws ActionException, SQLException, DAOException, ServiceException {
        userDAO.setConnection(connectionPool.getConnection());
        User beforeUpdateUser = userDAO.findById(TEST_USER_ID);
        userDAO.getConnection().close();

        ActionResult actionResult = buyProductAction.execute(request, response);
        assertEquals(JSP_PAGE_NAME_BILL, actionResult.getView());

        testUserBalance(beforeUpdateUser);
        testTransaction();
    }

    public void testUserBalance(User beforeUpdateUser) throws DAOException, SQLException, ServiceException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(TEST_USER_ID);

        Product product = productService.findById(Integer.parseInt(TEST_PRODUCT_ID));
        assertEquals(beforeUpdateUser.getBalance() - product.getPrice(), user.getBalance());
    }

    public void testTransaction() throws ServiceException {
        List<UserTransaction> transactions = transactionService.getAll(TEST_USER_ID);
        Product product = productService.findById(Integer.parseInt(TEST_PRODUCT_ID));
        assertEquals(transactions.get(transactions.size()-1).getSum(), product.getPrice());
        assertEquals(transactions.get(transactions.size()-1).getTransaction().getName(), TRANSACTION_NAME_BUY_PRODUCT);
    }
}
