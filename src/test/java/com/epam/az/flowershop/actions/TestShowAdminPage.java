package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.ShowAdminPage;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestShowAdminPage {
     String JSP_PAGE_NAME_ADMIN = "admin";
     String ATTRIBUTE_NAME_USERS = "users";
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private ShowAdminPage showAdminPage = new ShowAdminPage();
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO;

    @Test
    public void testAllUser() throws ActionException, SQLException, DAOException {
        ActionResult actionResult = showAdminPage.execute(request, response);
        assertEquals(JSP_PAGE_NAME_ADMIN, actionResult.getView());

        List<User> userList = (List<User>) request.getAttribute(ATTRIBUTE_NAME_USERS);
        assertEquals(allUsersInDbSize(), userList.size());
    }

    public int allUsersInDbSize() throws SQLException, DAOException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        List<User> users = userDAO.getAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getDeleteDay() != null) {
                users.remove(i);
            }
        }
        return users.size();
    }
}
