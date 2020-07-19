package dao;

import models.Employee;

import java.util.List;

public interface EmployeeDao {
    //create
    void add(Employee employee);

    //read
    List<Employee> getAll();

    // READ
    Employee findById(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAllEmployees();
}
