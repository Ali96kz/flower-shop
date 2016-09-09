package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOnlineVitrineAction extends AbstractVitrine{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Integer> pageNumber = addPageNumber();
        setPaginationList(req, pageNumber);
        return new ActionResult("vitrine");
    }
}
