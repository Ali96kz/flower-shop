package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.OnlineVitrineValidator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVitrine implements Action {
    private static final Logger logger = LoggerFactory.getLogger(AbstractVitrine.class);
    private List<Integer> pageNumbers;
    private ProductService productService = new ProductService();
    private OnlineVitrineValidator onlineVitrineValidator = new OnlineVitrineValidator();
    private StringAdapter stringAdapter = new StringAdapter();
    private PaginatedList paginatedList;

    public void setPaginationList(HttpServletRequest req) throws ActionException {
        int pageNumber;
        try {
            paginatedList = new PaginatedList(PAGE_SIZE, productService.getAllNotDeleteProduct());
            List<String> erorrMsg = onlineVitrineValidator.isValidate(req);

            if (erorrMsg.size() > 0) {
                pageNumber = 0;
                req.setAttribute(ATTRIBUTE_PRODUCTS, paginatedList.getPage(pageNumber));
                return;
            }

            String page = req.getParameter(PARAMETER_NAME_PAGE);
            pageNumber = stringAdapter.toInt(page);

            if (pageNumber > paginatedList.getPageNumber()) {
                pageNumber = 0;
            }

            req.setAttribute(ATTRIBUTE_PRODUCTS, paginatedList.getPage(pageNumber));
        } catch (ValidatorException e) {
            logger.error("can't isValidate object", e);
            throw new ActionException("can't isValidate", e);
        } catch (ServiceException e) {
            logger.error("can't isValidate object", e);
            throw new ActionException("can't get all products", e);
        }
    }

    public void setPage(HttpServletRequest req) {
        if (pageNumbers == null || pageNumbers.size() != paginatedList.getPageNumber()) {
            pageNumbers = new ArrayList<>();
            for (int i = 0; i < paginatedList.getPageNumber(); i++) {
                pageNumbers.add(i);
            }
        }
        req.setAttribute(REQUEST_ATTRIBUTE_PAGE_LIST, pageNumbers);
    }
}
