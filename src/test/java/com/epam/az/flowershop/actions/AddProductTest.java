package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.AddProductAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.ProductDAO;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class AddProductTest {
    private ConnectionPool connectionPool = new ConnectionPool();
    private AddProductAction addProductAction = new AddProductAction();
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private ProductDAO productDAO = new ProductDAO();
    public static final String PARAMETER_NAME_ORIGIN_ID = "originId";
    public static final String PARAMETER_NAME_VISUAL_PARAMETERS_ID = "visualParametersId";
    public static final String PARAMETER_NAME_FLOWER_TYPE_ID = "flowerTypeId";
    public static final String PARAMETER_NAME_GROWING_CONDITION_ID = "growingConditionId";
    public static final String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    public static final String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    public static final String PARAMETER_NAME_DESCRIPTION = "description";
    public static final String PARAMETER_NAME_PRICE_PRICE = "price";


    public AddProductTest() throws ActionException {
    }
    @Before
    public void initRequestWithCorrectParameters() throws SQLException {
        Random random = new Random(System.currentTimeMillis());
        request.setParameter(PARAMETER_NAME_AVERAGE_HEIGHT, String.valueOf(random.nextInt(Integer.MAX_VALUE-1)));
        request.setParameter(PARAMETER_NAME_FLOWER_NAME, "Gladiolus");
        request.setParameter(PARAMETER_NAME_DESCRIPTION, "This is new product added from test");
        request.setParameter(PARAMETER_NAME_ORIGIN_ID, String.valueOf(random.nextInt(7)+1));
        request.setParameter(PARAMETER_NAME_VISUAL_PARAMETERS_ID, String.valueOf(random.nextInt(3)+1));
        request.setParameter(PARAMETER_NAME_PRICE_PRICE, String.valueOf(random.nextInt(Integer.MAX_VALUE)-1));
        request.setParameter(PARAMETER_NAME_FLOWER_TYPE_ID, String.valueOf(random.nextInt(5)+1));
        request.setParameter(PARAMETER_NAME_GROWING_CONDITION_ID, String.valueOf(random.nextInt(5)+1));

    }

    @Test
    public void testAddNormalProduct() throws ActionException, SQLException, DAOException {
        ActionResult actionResult = addProductAction.execute(request, response);
        String url = actionResult.getView();
        int generateId = getProductIdFromUrl();
        Product product = productDAO.findById(generateId);

        assertEquals(request.getParameter(PARAMETER_NAME_AVERAGE_HEIGHT), product.getFlower().);

    }

    public int getProductIdFromUrl(){
        return 6;
    }

}
