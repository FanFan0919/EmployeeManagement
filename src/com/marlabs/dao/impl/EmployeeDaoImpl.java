package com.marlabs.dao.impl;

import com.marlabs.dao.EmployeeDao;
import com.marlabs.domain.Employee;
import com.marlabs.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Employee> findAll() {
        String sql = "SELECT id, firstName, lastName, age, salary, aptno, street, city, state " +
                     "FROM employees LEFT JOIN address ON employees.aid = address.aid";
        List<Employee> employees = template.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employees;
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        String sql1 = "insert into employees values(null, ?, ?, ?, ?, null, 0)";
        template.update(sql1,
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getAge(),
                        employee.getSalary());

        // find inserted eid and set address id = eid
        String sql2 = "select id from employees where firstName = ? and lastName = ? and age = ? and salary = ?";
        int currentId = template.queryForObject(sql2, Integer.class,
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getAge(),
                                employee.getSalary());

        //update address id in employees
        String sql3 = "update employees set aid = ? where id = ?";
        template.update(sql3, currentId, currentId);

        // insert address properties into address table
        String sql4 = "insert into address values(?, ?, ?, ?, ?)";
        template.update(sql4, currentId,
                employee.getAptno(),
                employee.getStreet(),
                employee.getCity(),
                employee.getState());
    }

    @Override
    @Transactional
    public void delete(int id) {
        String sql = "delete from employees where id = ?";
        template.update(sql, id);
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from employees";
        return template.queryForObject(sql, Integer.class);
    }

    // findAll() + LIMIT when query in database
    @Override
    public List<Employee> findByPages(int start, int rows) {
        String sql = "SELECT id, firstName, lastName, age, salary, aptno, street, city, state " +
                     "FROM employees LEFT JOIN address ON employees.aid = address.aid LIMIT ? , ?";
        List<Employee> employees = template.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), start, rows);
        return employees;
    }
}
