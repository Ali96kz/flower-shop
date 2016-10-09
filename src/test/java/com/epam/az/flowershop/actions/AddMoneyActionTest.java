package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.AddMoneyAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class AddMoneyActionTest {
    public static final String JSP_PAGE_NAME_CASH = "cash";
    public static final String PARAMETER_NAME_MONEY = "money";
    public static final String SESSION_PARAMETER_NAME_USER_ID = "userId";
    public static final int TEST_USER_ID = 1;
    private Random random = new Random();
   public static final Integer ADD_MONEY_TRANSACTION_ID = 1;
    private UserDAO userDAO;
    private UserTransactionDAO transactionDAO;
    private ConnectionPool connectionPool = new ConnectionPool();
    private AddMoneyAction addMoneyAction = new AddMoneyAction();
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();


    public AddMoneyActionTest() throws ActionException {
    }

    @Test
    public void testWithNormalMoney() throws ActionException, SQLException, DAOException {
        String ADD_MONEY_VALUE = String.valueOf(random.nextInt(36000)+1000);
        User beforeUpdate = getUncacheUserById(TEST_USER_ID);
        session.setAttribute(SESSION_PARAMETER_NAME_USER_ID, TEST_USER_ID);
        request.setParameter(PARAMETER_NAME_MONEY, ADD_MONEY_VALUE);
        request.setHttpSession(session);

        ActionResult actionResult = addMoneyAction.execute(request, response);
        assertEquals(JSP_PAGE_NAME_CASH, actionResult.getView());

        User afterUpdate = getUncacheUserById(TEST_USER_ID);
        assertEquals(beforeUpdate.getBalance()+Integer.parseInt(ADD_MONEY_VALUE), afterUpdate.getBalance());

        transactionDAO = new UserTransactionDAO();
        transactionDAO.setConnection(connectionPool.getConnection());
        List<UserTransaction> transactions = transactionDAO.getAll(TEST_USER_ID);
        assertEquals(transactions.get(transactions.size() - 1).getSum(), Integer.parseInt(ADD_MONEY_VALUE));
        assertEquals(transactions.get(transactions.size()-1).getTransaction().getId(), ADD_MONEY_TRANSACTION_ID);
    }
    @Test
    public void testMoneyBelowZero() throws ActionException, SQLException, DAOException {
        String ADD_MONEY_VALUE = String.valueOf(random.nextInt(36000)*(-1));
        User beforeUpdate = getUncacheUserById(TEST_USER_ID);
        session.setAttribute(SESSION_PARAMETER_NAME_USER_ID, TEST_USER_ID);
        request.setParameter(PARAMETER_NAME_MONEY, ADD_MONEY_VALUE);
        request.setHttpSession(session);

        ActionResult actionResult = addMoneyAction.execute(request, response);
        assertEquals(JSP_PAGE_NAME_CASH, actionResult.getView());

        User afterUpdate = getUncacheUserById(TEST_USER_ID);
        assertEquals(beforeUpdate.getBalance(), afterUpdate.getBalance());
    }

    @Test
    public void testWithFloatNumber() throws SQLException, DAOException, ActionException {
        String ADD_MONEY_VALUE = String.valueOf(random.nextDouble()*(-1));
        User beforeUpdate = getUncacheUserById(TEST_USER_ID);
        session.setAttribute(SESSION_PARAMETER_NAME_USER_ID, TEST_USER_ID);
        request.setParameter(PARAMETER_NAME_MONEY, ADD_MONEY_VALUE);
        request.setHttpSession(session);

        ActionResult actionResult = addMoneyAction.execute(request, response);
        assertEquals(JSP_PAGE_NAME_CASH, actionResult.getView());

        User afterUpdate = getUncacheUserById(TEST_USER_ID);
        assertEquals(beforeUpdate.getBalance(), afterUpdate.getBalance());

    }

    public User getUncacheUserById(int id) throws SQLException, DAOException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(id);
        userDAO.getConnection().close();
        return user;
    }

}
