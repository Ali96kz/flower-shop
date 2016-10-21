package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Origin;

import java.util.List;

public class OriginService {
    public static final Class<OriginDAO> ORIGIN_DAO_CLASS = OriginDAO.class;
    private ProxyService proxyService = new ProxyService(ORIGIN_DAO_CLASS);

    public List<Origin> getAll() throws ServiceException {
        List<Origin> origins = proxyService.getAll();
        return origins;
    }

    public Origin findById(int id) throws ServiceException {
        Origin origin = (Origin) proxyService.findById(id);
        return origin;
    }
}
