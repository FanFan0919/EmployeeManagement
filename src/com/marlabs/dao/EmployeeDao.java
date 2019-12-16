package com.marlabs.dao;

import com.marlabs.domain.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> findAll();

    void add(Employee employee);

    void delete(int id);

    int findTotalCount();

    List<Employee> findByPages(int start, int rows);
}
