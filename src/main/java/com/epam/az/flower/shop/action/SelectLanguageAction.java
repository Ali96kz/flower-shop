package com.epam.az.flower.shop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class SelectLanguageAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(SelectLanguageAction.class);
    private static final String LANG = "lang";
    private static final String REFERER = "referer";
    private static final int MINUT = 60;
    private static final int DAY = 24;
    private static final int SEC = 60;
    private static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String language = req.getParameter(LANG);
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(DAY * MINUT * SEC);
        resp.addCookie(cookie);

        try {
            req.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error("can't set character encoding", e);
            throw new ActionException("can't set character encoding", e);
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
