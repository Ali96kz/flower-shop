package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.AddMoneyAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AddMoneyTest {
    private ConnectionPool connectionPool = new ConnectionPool();
    private TestHttpRequest request = new TestHttpRequest();
    private HttpSession session = new TestSession();
    private UserDAO userDAO = new UserDAO();
    private UserTransactionDAO userTransactionDAO = new UserTransactionDAO();
    private AddMoneyAction addMoneyAction = new AddMoneyAction();
    private TestHttpResponse response = new TestHttpResponse();

    public static final String PARAMETER_MONEY = "money";
    public static final int TEST_USER_ID = 1;
    public static final String TEST_DONATE_SUM = "45";
    public static final String PARAMETER_USER_ID = "userId";


    public AddMoneyTest() throws ActionException {
    }
    @Test
    public void testWithNegativeNumber() throws SQLException, DAOException, ActionException {
        Random random = new Random(System.currentTimeMillis());
        int afterBalance, beforeBalance;

        session.setAttribute(PARAMETER_USER_ID, TEST_USER_ID);
        request.setHttpSession(session);

        for (int i = 0; i < random.nextInt(10); i++) {
            beforeBalance = getUser(TEST_USER_ID).getBalance();

            request.setParameter(PARAMETER_MONEY, String.valueOf(random.nextInt(5000)*(-1)));
            System.out.println(random.nextInt(5000)*(-1));
            addMoneyAction.execute(request, response);

            afterBalance = getUser(TEST_USER_ID).getBalance();

            assertEquals(beforeBalance, afterBalance);
            userDAO = new UserDAO();
        }

    }
    public User getUser(int userId) throws DAOException, SQLException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(userId);
        userDAO.getConnection().close();
        return user;
    }

    @Test
    public void testWithCorrectNumber() throws DAOException, SQLException, ActionException {
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(TEST_USER_ID);
        userDAO.getConnection().close();

        session.setAttribute(PARAMETER_USER_ID, TEST_USER_ID);
        request.setParameter(PARAMETER_MONEY, TEST_DONATE_SUM);
        request.setHttpSession(session);
        addMoneyAction.execute(request, response);

        checkBalance(TEST_USER_ID, user.getBalance()+Integer.parseInt(TEST_DONATE_SUM));
        checkTransaction(TEST_USER_ID, Integer.parseInt(TEST_DONATE_SUM));
    }

    public void checkTransaction(int userId, int transactionSum) throws DAOException, SQLException {
        userTransactionDAO.setConnection(connectionPool.getConnection());
        List<UserTransaction> userTransactions = userTransactionDAO.getAll(userId);
        userTransactionDAO.getConnection().close();
        assertTrue(userTransactions.get(userTransactions.size() -1).getSum() == transactionSum);
    }

    public void checkBalance(int userId, int afterAddedBalance) throws DAOException, SQLException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(userId);
        userDAO.getConnection().close();
        assertEquals(afterAddedBalance, user.getBalance());
    }
}
