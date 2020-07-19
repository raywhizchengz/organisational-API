package dao;

import models.Department;
import models.Employee;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oEmployeeDao employeeDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        employeeDao = new Sql2oEmployeeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Department testDepartment(){
        return new Department("accounts", "financial issues",8);
    }

    @Test
    public void addingADepartmentSetsId() throws Exception{
        Department department = testDepartment();
        int departmentId = department.getId();
        departmentDao.add(department);
        assertNotEquals(departmentId, department.getId());
    }

    @Test
    public void findByIdCanFindDepartmentById() throws Exception{
        Department department = testDepartment();
        departmentDao.add(department);
        Department foundDepartment = departmentDao.findById(department.getId());
        assertEquals(department, foundDepartment);
    }

    @Test
    public void getAllDepartmentReturnsAllCreatedDepartments() throws Exception{
        Department department = testDepartment();
        Department anotherDepartment = new Department("hr", "deals with all employees", 4);
        departmentDao.add(department);
        departmentDao.add(anotherDepartment);
        assertEquals(2, departmentDao.getAll().size());
    }

    @Test
    public void ifNoDepartmentExistsReturnsEmpty() throws Exception{
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void updatesDepartmentInformation() throws Exception{
        Department department = testDepartment();
        departmentDao.add(department);
        departmentDao.update(department.getId(), "security", "safety first", 4);
        Department newDepartment = departmentDao.findById(department.getId());
        assertNotEquals(department.getDeptName(), newDepartment.getDeptName());
        assertNotEquals(department.getDescription(), newDepartment.getDescription());
        assertNotEquals(department.getNoOfEmployees(), newDepartment.getNoOfEmployees());
    }

    @Test
    public void deleteByIdDeletesSingleDepartment() throws Exception {
        Department department = testDepartment();
        Department secondDepartment = new Department("hr", "deals with all employees", 4);
        departmentDao.add(department);
        departmentDao.add(secondDepartment);
        departmentDao.deleteById(department.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void deleteAllClearsAllDepartments() throws Exception{
        Department department = testDepartment();
        Department secondDepartment = new Department("hr", "deals with all employees", 4);
        departmentDao.add(department);
        departmentDao.add(secondDepartment);
        departmentDao.clearAllDepartments();
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void getAllEmployeesInDeptReturnsAllEmployeesInASpecificDept() throws Exception {
        Department department = testDepartment();
        departmentDao.add(department);
        int departmentId = department.getId();
        Employee employee1 = new Employee("one", "junior", departmentId);
        Employee employee2 = new Employee("two", "junior", departmentId);
        Employee employee3 = new Employee("three", "junior", departmentId);
        employeeDao.add(employee1);
        employeeDao.add(employee2);
        assertEquals(2, departmentDao.getAllEmployeesInADept(departmentId).size());
        assertTrue(departmentDao.getAllEmployeesInADept(departmentId).contains(employee1));
        assertTrue(departmentDao.getAllEmployeesInADept(departmentId).contains(employee2));
        assertFalse(departmentDao.getAllEmployeesInADept(departmentId).contains(employee3));
    }
}