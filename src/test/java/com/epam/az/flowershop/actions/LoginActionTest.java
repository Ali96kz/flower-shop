package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.LoginAction;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginActionTest {
    private static final String JSP_PAGE_NAME_PROFILE = "profile";
    private static final String PARAMETER_NICK_NAME = "nickName";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String ATTRIBUTE_NAME_USER_ID = "userId";
    private static final String JSP_PAGE_NAME_LOGIN = "login";
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private LoginAction loginAction = new LoginAction();

    @Test
    public void testWithNormalParameter() throws ActionException {
        request.setParameter(PARAMETER_NICK_NAME, "admin");
        request.setParameter(PARAMETER_PASSWORD, "1234567");
        request.setHttpSession(session);

        ActionResult actionResult = loginAction.execute(request, response);

        assertEquals(actionResult.getView(), JSP_PAGE_NAME_PROFILE);
        assertEquals(session.getAttribute(ATTRIBUTE_NAME_USER_ID), 1);
    }

    @Test
    public void testWithUnexistUser() throws ActionException {
        request.setParameter(PARAMETER_NICK_NAME, "testUnexist");
        request.setParameter(PARAMETER_PASSWORD, "laksdj");
        request.setHttpSession(session);

        ActionResult actionResult = loginAction.execute(request, response);

        assertEquals(actionResult.getView(), JSP_PAGE_NAME_LOGIN);
        assertEquals(session.getAttribute(ATTRIBUTE_NAME_USER_ID), null);

    }
}
