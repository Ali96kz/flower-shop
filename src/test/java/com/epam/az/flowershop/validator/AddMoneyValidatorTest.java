package com.epam.az.flowershop.validator;

import com.epam.az.flower.shop.validator.AddMoneyValidator;
import com.epam.az.flowershop.TestHttpRequest;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AddMoneyValidatorTest {
     String PARAMETER_MONEY = "money";
    private TestHttpRequest testHttpRequest = new TestHttpRequest();
    private AddMoneyValidator addMoneyValidator = new AddMoneyValidator();

    @Test
    public void testWithNormalValue() {
        testHttpRequest.setParameter(PARAMETER_MONEY, "45");
        List<String> errorMsg = addMoneyValidator.isValidate(testHttpRequest);
        assertEquals("Incorrect isValidate right value", 0, errorMsg.size());
    }

    @Test
    public void testWithEmptyValue() {
        testHttpRequest.setParameter(PARAMETER_MONEY, "");
        List<String> errorMsg = addMoneyValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect isValidate right value", errorMsg.contains("You didn't insert money"));
    }

    @Test
    public void testWithFractionalValue() {
        testHttpRequest.setParameter(PARAMETER_MONEY, "45.5");
        List<String> errorMsg = addMoneyValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect isValidate right value", errorMsg.contains("please insert money"));
    }

    @Test
    public void testWithNullValue() {
        testHttpRequest.setParameter(PARAMETER_MONEY, null);
        List<String> errorMsg = addMoneyValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect isValidate right value", errorMsg.contains("You didn't insert money"));
    }

    @Test
    public void testToNumberMoreThenInt() {
        long test = Integer.MAX_VALUE + 64846;
        testHttpRequest.setParameter(PARAMETER_MONEY, String.valueOf(test));
        List<String> errorMsg = addMoneyValidator.isValidate(testHttpRequest);
        assertTrue("Incorrect isValidate right value", errorMsg.contains("please insert money"));
    }

}
