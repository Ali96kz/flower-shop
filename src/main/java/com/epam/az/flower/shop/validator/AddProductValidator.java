package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.adapter.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddProductValidator implements Validator {
    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public List<String> isValidate(HttpServletRequest request) {

        List<String> errorMsg = new ArrayList<>();

        if(request.getParameter("flowerName") == null){
            errorMsg.add("Please insert flower name");
            return errorMsg;
        }

        if(request.getParameter("description") == null){
            errorMsg.add("Please insert description");
            return errorMsg;
        }

        if (request.getParameter("price") == null) {
            errorMsg.add("you must insert a price");
            return errorMsg;
        }

        if (request.getParameter("averageHeight") == null) {
            errorMsg.add("you must insert a average height");
            return errorMsg;
        }

        Integer price = stringAdapter.toInt(request.getParameter("price"));
        Integer averageHeight = stringAdapter.toInt(request.getParameter("averageHeight"));

        if(request.getParameter("description").length() < 16){
            errorMsg.add("description must contain more than 16 characters");
        }
        if (price < 0 ){
            errorMsg.add("price couldn't be < 0");
        }

        if (averageHeight < 0) {
            errorMsg.add("average height can't be less than 0");
        }

        return errorMsg;
    }
}
