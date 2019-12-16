package com.marlabs.service;

import com.marlabs.domain.Employee;
import com.marlabs.domain.PageBean;

import java.util.List;

public interface EmployeeService {
    // List All Employees
    public List<Employee> findAll();

    // Add Employee
    void addEmployee(Employee employee);

    // Delete one Employee
    void deleteEmployee(String id);

    // Delete Selected Employees
    void delSelected(String[] ids);

    // Divide pages
    PageBean<Employee> findEmployeeByPage(String currentPage, String rows);
}
