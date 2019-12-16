package com.marlabs.dao;

import com.marlabs.domain.Administrator;

public interface AdministratorDao {
    public Administrator findAdminByUsernameAndPassword(String username, String password);
}
