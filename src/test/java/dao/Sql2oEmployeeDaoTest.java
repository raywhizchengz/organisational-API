package dao;

import models.Employee;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEmployeeDaoTest {
    private static Connection conn;
    private static Sql2oEmployeeDao employeeDao;

//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        employeeDao = new Sql2oEmployeeDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//    }

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        employeeDao = new Sql2oEmployeeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        employeeDao.clearAllEmployees();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    Employee testEmployee(){
        return new Employee("first", "junior",1);
    }

    @Test
    public void databaseSetsIdWhenEmployeeIsAdded() throws Exception{
        Employee employee = testEmployee();
        int employeeId = employee.getId();
        employeeDao.add(employee);
        assertNotEquals(employeeId, employee.getId());
    }

    @Test
    public void listOfAllEmployeesCanBeRetrievedByGetAll() throws Exception{
        Employee employee = testEmployee();
        employeeDao.add(employee);
        assertEquals(1, employeeDao.getAll().size());
    }

    @Test
    public void ifNoEmployeeExistsReturnsZero() throws Exception{
        assertEquals(0, employeeDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesASingleEmployee() throws Exception {
        Employee employee = testEmployee();
        employeeDao.add(employee);
        employeeDao.deleteById(employee.getId());
        assertEquals(0, employeeDao.getAll().size());
    }

    @Test
    public void anEmployeeCanBeFoundById() throws Exception{
        Employee employee = testEmployee();
        employeeDao.add(employee);
        Employee foundEmployee = employeeDao.findById(employee.getId());
        assertEquals(foundEmployee, employeeDao.findById(employee.getId()));
    }

    @Test
    public void clearAllDeletesAllEmployees() throws Exception{
        Employee employee = testEmployee();
        Employee secondEmployee = new Employee("second", "mid-level",2);
        employeeDao.add(employee);
        employeeDao.add(secondEmployee);
        employeeDao.clearAllEmployees();
        assertEquals(0, employeeDao.getAll().size());
    }

    @Test
    public void departmentIdIsReturnedCorrectly() throws Exception {
        Employee employee = testEmployee();
        int originalDepartmentId = employee.getDeptId();
        employeeDao.add(employee);
        assertEquals(originalDepartmentId, employeeDao.findById(employee.getId()).getDeptId());
    }
}