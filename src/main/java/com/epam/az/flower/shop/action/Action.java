package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) ;
}
