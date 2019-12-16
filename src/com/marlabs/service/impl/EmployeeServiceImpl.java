package com.marlabs.service.impl;

import com.marlabs.dao.EmployeeDao;
import com.marlabs.dao.impl.EmployeeDaoImpl;
import com.marlabs.domain.Employee;
import com.marlabs.domain.PageBean;
import com.marlabs.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao dao = new EmployeeDaoImpl();
    @Override
    public List<Employee> findAll() {
        return dao.findAll();
    }

    @Override
    public void addEmployee(Employee employee) {
        dao.add(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public void delSelected(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for(String id : ids) {
            dao.delete(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<Employee> findEmployeeByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        PageBean<Employee> pb = new PageBean<>();
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        int totalPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;
        pb.setTotalPage(totalPage);

        if (currentPage <= 0) currentPage = 1;
        if (currentPage > totalPage) currentPage = totalPage;
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int start = (currentPage - 1) * rows;
        List<Employee> list = dao.findByPages(start, rows);
        pb.setList(list);
        return pb;
    }
}
