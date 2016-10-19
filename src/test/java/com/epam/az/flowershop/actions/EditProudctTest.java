package com.epam.az.flowershop.actions;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.action.EditProductAction;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.dao.ProductDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flowershop.TestHttpRequest;
import com.epam.az.flowershop.TestHttpResponse;
import com.epam.az.flowershop.TestSession;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class EditProudctTest {
    public static final String PARAMETER_PRODUCT_ID = "productId";
    public static final String PARAMETER_NAME_ORIGIN_ID = "originId";
    public static final String PARAMETER_NAME_VISUAL_PARAMETERS_ID = "visualParametersId";
    public static final String PARAMETER_NAME_FLOWER_TYPE_ID = "flowerTypeId";
    public static final String PARAMETER_NAME_GROWING_CONDITION_ID = "growingConditionId";
    public static final String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";
    public static final String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    public static final String PARAMETER_NAME_DESCRIPTION = "description";
    public static final String PARAMETER_NAME_PRICE = "price";
    private EditProductAction editProductAction = new EditProductAction();
    private TestHttpRequest request = new TestHttpRequest();
    private TestHttpResponse response = new TestHttpResponse();
    private TestSession session = new TestSession();
    private ProductDAO productDAO;
    private FlowerDAO flowerDAO;
    private ConnectionPool connectionPool = new ConnectionPool();
    private String flowerName, description, price, originId, productId;
    private String visualParametersId, flowerTypeId, growingConditionId, averageHeight;

    @Before
    public void init() {
        Random random = new Random(System.currentTimeMillis());
        flowerName = UUID.randomUUID().toString().substring(0, 6);

        description = UUID.randomUUID().toString().substring(0, 20);
        price = String.valueOf(random.nextInt(5000) - 100);
        visualParametersId = String.valueOf(random.nextInt(3) + 1);
        flowerTypeId = String.valueOf(random.nextInt(5) + 1);
        growingConditionId = String.valueOf(random.nextInt(5) + 1);
        originId = String.valueOf(random.nextInt(6) + 1);
        averageHeight = String.valueOf(random.nextInt(50000));
        productId = String.valueOf(random.nextInt(15) + 1);

        request.setParameter(PARAMETER_NAME_AVERAGE_HEIGHT, averageHeight);
        request.setParameter(PARAMETER_PRODUCT_ID, productId);
        request.setParameter(PARAMETER_NAME_FLOWER_NAME, flowerName);
        request.setParameter(PARAMETER_NAME_DESCRIPTION, description);
        request.setParameter(PARAMETER_NAME_ORIGIN_ID, originId);
        request.setParameter(PARAMETER_NAME_VISUAL_PARAMETERS_ID, visualParametersId);
        request.setParameter(PARAMETER_NAME_PRICE, price);
        request.setParameter(PARAMETER_NAME_FLOWER_TYPE_ID, flowerTypeId);
        request.setParameter(PARAMETER_NAME_GROWING_CONDITION_ID, growingConditionId);
    }

    @Test
    public void testWithNormalParameter() throws ActionException, SQLException, DAOException {
        ActionResult actionResult = editProductAction.execute(request, response);
        assertEquals(Integer.parseInt(productId), getProductIdFromUrl(actionResult.getView()));

        Product product = getProductById(Integer.parseInt(productId));
        assertEquals(Integer.parseInt(flowerTypeId), (int) product.getFlower().getFlowerType().getId());
        assertEquals(flowerName, product.getFlower().getName());
        assertEquals(description, product.getDescription());
        assertEquals(Integer.parseInt(averageHeight), product.getFlower().getAverageHeight());
        assertEquals(Integer.parseInt(originId), (int) product.getOrigin().getId());
        assertEquals(Integer.parseInt(visualParametersId), (int) product.getFlower().getVisualParameters().getId());
        assertEquals(Integer.parseInt(flowerTypeId), (int) product.getFlower().getFlowerType().getId());
        assertEquals(Integer.parseInt(growingConditionId), (int) product.getFlower().getGrowingCondition().getId());
    }

    @Test
    public void testWithLongFlowerName() throws ActionException {
        flowerName = UUID.randomUUID().toString();
        request.setParameter(PARAMETER_NAME_FLOWER_NAME, flowerName);
        ActionResult actionResult = editProductAction.execute(request, response);
        List<String> errorMsg = (List<String>) request.getAttribute("errorMsg");
        assertEquals(actionResult.getView(), "product-edit");
        assertEquals(errorMsg.size(), 1);
        assertTrue(errorMsg.contains("flower name must contain min 4 max 16"));
    }

    public int getProductIdFromUrl(String url) {
        System.out.println(url);
        int result = 0, multiply = 1;
        for (int i = url.length() - 1; i >= 0; i--) {
            if (url.charAt(i) == '=') {
                break;
            }
            result += multiply * (Integer.parseInt(String.valueOf(url.charAt(i))));
            multiply *= 10;
        }
        return result;
    }

    public Product getProductById(int id) throws SQLException, DAOException {
        productDAO = new ProductDAO();
        flowerDAO = new FlowerDAO();
        Connection productDAOConnection = connectionPool.getConnection();
        Connection flowerDaoConnection = connectionPool.getConnection();
        productDAO.setConnection(productDAOConnection);
        flowerDAO.setConnection(flowerDaoConnection);

        Product product = productDAO.findById(id);
        Flower flower = flowerDAO.findById(product.getFlower().getId());
        product.setFlower(flower);
        return product;
    }
}
