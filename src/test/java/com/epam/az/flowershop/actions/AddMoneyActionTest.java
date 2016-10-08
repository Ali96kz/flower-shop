package com.epam.az.flowershop.actions;

import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

public class AddMoneyActionTest {
    public static final int TEST_USER_ID = 1;
    public static final String PARAMETER_NAME_MONEY = "money";
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();

    @Before
    public void initNormalalue(){
        session.setAttribute("userId", TEST_USER_ID);
        request.setParameter(PARAMETER_NAME_MONEY, "45");
    }

    @Test
    public void testWithNormalMoney(){

    }
}
