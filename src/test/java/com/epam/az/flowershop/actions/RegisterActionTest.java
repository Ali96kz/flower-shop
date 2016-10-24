package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.RegisterAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flowershop.AbstractTest;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class RegisterActionTest extends AbstractTest {
     String PARAMETER_FIRST_NAME = "firstName";
     String PARAMETER_NICK_NAME = "nickName";
     String PARAMETER_LAST_NAME = "lastName";
     String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
     String PARAMETER_PASSWORD = "password";
     String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
     String JSP_PAGE_NAME_PROFILE = "profile";
     String PARAMETER_USER_ID = "userId";
     String TEST_USER_BIRTHDAY = "1996-12-11";
     String TEST_USER_FIRST_NAME = "Ali";
     String TEST_USER_LAST_NAME = "Zhagparov";
     String JSP_PAGE_NAME_REGISTRATION = "registration";

    private String userPassword;
    private String confirmUserPassword;
    private RegisterAction registerAction = new RegisterAction();
    private TestSession session = new TestSession();
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private String nickname;
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO;
    private Integer customerRoleId = 3;
    private Hasher hasher = new Hasher();

    @Before
    public void init() {
        UUID nicknameUUID = UUID.randomUUID();
        UUID userPasswordUUID = UUID.randomUUID();
        nickname = nicknameUUID.toString().substring(0, 12);
        confirmUserPassword = userPasswordUUID.toString().substring(0, 10);
        userPassword = confirmUserPassword;

        request.setParameter(PARAMETER_FIRST_NAME, TEST_USER_FIRST_NAME);
        request.setParameter(PARAMETER_LAST_NAME, TEST_USER_LAST_NAME);
        request.setParameter(PARAMETER_NICK_NAME, nickname);
        request.setParameter(PARAMETER_PASSWORD, userPassword);
        request.setParameter(PARAMETER_CONFIRM_PASSWORD, userPassword);
        request.setParameter(PARAMETER_DATE_BIRTHDAY, TEST_USER_BIRTHDAY);
        request.setHttpSession(session);
    }

    @Test
    public void testWithNormalValue() throws Exception {
        ActionResult actionResult = registerAction.execute(request, response);
        User user = getUncacheUserById((Integer) request.getSession().getAttribute(PARAMETER_USER_ID));

        assertEquals(JSP_PAGE_NAME_PROFILE, actionResult.getView());
        assertEquals(0, user.getBalance());
        assertEquals(user.getDateBirthday().toString(), TEST_USER_BIRTHDAY);
        assertEquals(user.getFirstName(), TEST_USER_FIRST_NAME);
        assertEquals(user.getLastName(), TEST_USER_LAST_NAME);
        assertEquals(user.getNickName(), nickname);
        assertEquals(user.getUserRole().getId(), customerRoleId);
        assertEquals(user.getPassword(), hasher.hash(userPassword));
    }

    @Test
    public void testWithDifferentPassword() throws ActionException, SQLException, DAOException {
        request.setParameter(PARAMETER_CONFIRM_PASSWORD, "llllllll");
        ActionResult actionResult = registerAction.execute(request, response);
        assertEquals(0, getUncacheUserIdByNickname(nickname));
        assertEquals(JSP_PAGE_NAME_REGISTRATION, actionResult.getView());
    }

    @Test
    public void testWithIncorrecctDateBirthday() throws SQLException, DAOException, ActionException {
        request.setParameter(PARAMETER_DATE_BIRTHDAY, "454asdsd");
        ActionResult actionResult = registerAction.execute(request, response);
        assertEquals(0, getUncacheUserIdByNickname(nickname));
        assertEquals(JSP_PAGE_NAME_REGISTRATION, actionResult.getView());
    }


    public int getUncacheUserIdByNickname(String nickname) throws SQLException, DAOException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        int userId = userDAO.findByCredentials(nickname);
        userDAO.getConnection().close();

        return userId;
    }
}