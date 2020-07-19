package dao;
import models.Department;
import models.DepartmentNews;
import models.Employee;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);

    //read
    List<Department> getAll();

    Department findById(int id);

    //update
    void update(int id, String deptName, String description, int noOfEmployees);

    //delete
    void deleteById(int id);
    void clearAllDepartments();

    List<Employee> getAllEmployeesInADept(int deptId);
//    List<DepartmentNews> getAllNewsInADept(int deptId);
}
