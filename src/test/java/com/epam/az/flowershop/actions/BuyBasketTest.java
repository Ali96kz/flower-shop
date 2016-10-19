package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.BuyBasketAction;
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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BuyBasketTest {
    public static final int TEST_USER_ID_WITHOUT_MONEY = 3;
    public static final int TEST_USER_ID = 2;
    public static final String TRANSACTION_NAME_BUY_PRODUCT = "buy product";

    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private Basket basket = new Basket();
    private ProductService productService = new ProductService();
    private BuyBasketAction buyBasketAction;
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO = new UserDAO();
    private UserTransactionService transactionService = new UserTransactionService();


    @Before
    public void init() throws ServiceException, ActionException {
        buyBasketAction = new BuyBasketAction();
        basket = new Basket();
        basket.add(productService.findById(1));
        basket.add(productService.findById(2));
        basket.add(productService.findById(3));
        basket.add(productService.findById(4));

        session.setAttribute("userId", TEST_USER_ID_WITHOUT_MONEY);
        session.setAttribute("basket", basket);
        request.setHttpSession(session);
    }

    @Test
    public void testBasketWithSomeProducts() throws ActionException, SQLException, DAOException, ServiceException {
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE User SET User.balance = 15000 where User.id = " + TEST_USER_ID);
        connection.close();

        session.setAttribute("userId", TEST_USER_ID);

        User beforeUpdateUser = getUncacheUserById(TEST_USER_ID);
        ActionResult actionResult = buyBasketAction.execute(request, response);
        User user = getUncacheUserById(TEST_USER_ID);
        assertEquals("bill", actionResult.getView());
        assertEquals(beforeUpdateUser.getBalance() - basket.getSum(), user.getBalance());

        List<UserTransaction> transactions = transactionService.getAll(TEST_USER_ID_WITHOUT_MONEY);
        List<Product> products = basket.getProducts();

        for (int i = transactions.size() - 1, j = 1; i >= 0; i--, j++) {
            if (j < 4) {
                assertEquals(transactions.get(i).getSum(), products.get(j).getPrice());
                assertEquals(transactions.get(i).getTransaction().getName(), TRANSACTION_NAME_BUY_PRODUCT);
            }
            break;
        }

    }

    @Test
    public void testWithNullBasket() throws SQLException, DAOException, ActionException {
        session.setAttribute("basket", null);
        User beforeUpdateUser = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);

        ActionResult actionResult = buyBasketAction.execute(request, response);
        assertEquals(actionResult.getView(), "basket");

        User user = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);
        assertEquals(beforeUpdateUser.getBalance(), user.getBalance());

    }

    @Test
    public void testWithEmptyBasket() throws SQLException, DAOException, ActionException {
        session.setAttribute("basket", new Basket());
        User beforeUpdateUser = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);

        ActionResult actionResult = buyBasketAction.execute(request, response);
        assertEquals(actionResult.getView(), "basket");

        User user = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);
        assertEquals(beforeUpdateUser.getBalance(), user.getBalance());
    }

    @Test
    public void testUserDontHaveMoneyTobuyAllBasket() throws SQLException, DAOException, ActionException {

        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE User SET User.balance = 0 where User.id = " + TEST_USER_ID_WITHOUT_MONEY);
        User beforeUpdateUser = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);

        ActionResult actionResult = buyBasketAction.execute(request, response);

        assertEquals(actionResult.getView(), "basket");

        User user = getUncacheUserById(TEST_USER_ID_WITHOUT_MONEY);
        assertEquals(beforeUpdateUser.getBalance(), user.getBalance());

    }


    public User getUncacheUserById(int id) throws SQLException, DAOException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(id);
        userDAO.getConnection().close();
        return user;
    }
}
