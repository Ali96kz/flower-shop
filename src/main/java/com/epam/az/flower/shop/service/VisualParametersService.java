package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.VisualParametersDAO;
import com.epam.az.flower.shop.entity.VisualParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VisualParametersService {
    private static final Class<VisualParametersDAO> VISUAL_PARAMETERS_DAO_CLASS = VisualParametersDAO.class;
    private static Logger logger = LoggerFactory.getLogger(VisualParametersService.class);
    private ProxyService proxyService = new ProxyService(VISUAL_PARAMETERS_DAO_CLASS);

    public List<VisualParameters> getAll() throws ServiceException {
        List<VisualParameters> visualParameterses = proxyService.getAll();

        return visualParameterses;
    }

    public VisualParameters findById(int id) throws ServiceException {
        logger.info("try to find visual parameters id = {}", id);
        VisualParameters visualParameters = (VisualParameters) proxyService.findById(id);

        return visualParameters;
    }
}
