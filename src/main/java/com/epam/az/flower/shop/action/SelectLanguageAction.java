package com.epam.az.flower.shop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class SelectLanguageAction implements Action{
    private static Logger logger = LoggerFactory.getLogger(SelectLanguageAction.class);
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String language = req.getParameter("lang");
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie("lang", language);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);

        return new ActionResult(req.getHeader("referer"), true);

    }
}
