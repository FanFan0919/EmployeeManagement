package com.marlabs.dao.impl;

import com.marlabs.dao.AdministratorDao;
import com.marlabs.domain.Administrator;
import com.marlabs.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdministratorDaoImpl implements AdministratorDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Administrator findAdminByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from administrator where username = ? and password = ?";
            Administrator administrator = template.queryForObject(sql, new BeanPropertyRowMapper<Administrator>(Administrator.class), username, password);
            return administrator;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
