package com.marlabs.service.impl;

import com.marlabs.dao.AdministratorDao;
import com.marlabs.dao.impl.AdministratorDaoImpl;
import com.marlabs.domain.Administrator;
import com.marlabs.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    private AdministratorDao dao = new AdministratorDaoImpl();
    @Override
    public Administrator login(Administrator administrator) {
        return dao.findAdminByUsernameAndPassword(administrator.getUsername(), administrator.getPassword());
    }
}
