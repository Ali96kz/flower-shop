package com.epam.az.flowershop.validator;

import com.epam.az.flower.shop.validator.RegisterProfileValidator;
import com.epam.az.flower.shop.validator.ValidatorException;
import com.epam.az.flowershop.TestHttpRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RegisterValidator {
    public static final String PARAMETER_NICK_NAME = "nickName";
    public static final String PARAMETER_LAST_NAME = "lastName";
    public static final String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    public static final String PARAMETER_FIRST_NAME = "firstName";
    private RegisterProfileValidator registerProfileValidator = new RegisterProfileValidator();
    private TestHttpRequest testHttpRequest;

    public RegisterValidator() throws ValidatorException {
    }

    @Before
    public void init() {
        testHttpRequest = new TestHttpRequest();
        testHttpRequest.setParameter(PARAMETER_NICK_NAME, "ali96");
        testHttpRequest.setParameter(PARAMETER_LAST_NAME, "Zhagparov");
        testHttpRequest.setParameter(PARAMETER_FIRST_NAME, "Ali");
        testHttpRequest.setParameter(PARAMETER_DATE_BIRTHDAY, "1996-12-11");
        testHttpRequest.setParameter(PARAMETER_PASSWORD, "1234567");
        testHttpRequest.setParameter(PARAMETER_CONFIRM_PASSWORD, "1234567");
    }


    @Test
    public void testWithRightValue() throws ValidatorException {
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);
        assertEquals("Incorrect validate with right value", 0, errorMsg.size());
    }

    @Test
    public void testWithExistNickName() throws ValidatorException {
        testHttpRequest.setParameter(PARAMETER_NICK_NAME, "ali");
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect validate", errorMsg.contains("This nickname is busy, please insert another nickname"));
    }

    @Test
    public void testToDifferentPassword() throws ValidatorException {
        testHttpRequest.setParameter(PARAMETER_PASSWORD, "1234567");
        testHttpRequest.setParameter(PARAMETER_PASSWORD, "12345678");
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect validate", errorMsg.contains("Confirm password has a different value"));
    }

    @Test
    public void testToLongString() throws ValidatorException {
        testHttpRequest.setParameter(PARAMETER_LAST_NAME, "jhsafhadsfjsdfhsdhgfshjdagfasdd");
        testHttpRequest.setParameter(PARAMETER_FIRST_NAME, "jhsafhadsfjsdfhsdhgfshjdagfasd");
        testHttpRequest.setParameter(PARAMETER_NICK_NAME, "jhsafhadsfjsdfhsdhgfshjdagasdfd");
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);

        for (String s : errorMsg) {
            System.out.println(s);
        }

        assertTrue("Incorrect validate", errorMsg.contains("nick name must contain min 3 max 16"));
        assertTrue("Incorrect validate", errorMsg.contains("last name must contain min 3 max 16"));
        assertTrue("Incorrect validate", errorMsg.contains("first name must contain min 3 max 16"));
    }

    @Test
    public void testToMinString() throws ValidatorException {
        testHttpRequest.setParameter(PARAMETER_LAST_NAME, "s");
        testHttpRequest.setParameter(PARAMETER_FIRST_NAME, "s");
        testHttpRequest.setParameter(PARAMETER_NICK_NAME, "s");
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);

        assertTrue("Incorrect validate", errorMsg.contains("nick name must contain min 3 max 16"));
        assertTrue("Incorrect validate", errorMsg.contains("last name must contain min 3 max 16"));
        assertTrue("Incorrect validate", errorMsg.contains("first name must contain min 3 max 16"));
    }

    @Test
    public void testToIncorectDate() throws ValidatorException {
        testHttpRequest.setParameter(PARAMETER_DATE_BIRTHDAY, "1995656");
        List<String> errorMsg = registerProfileValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect validate", errorMsg.contains("You insert incorrect date Example: 1996-12-11"));
    }
}
