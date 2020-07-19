package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    Employee testEmployee(){
        return new Employee("two","junior",1);
    }

    @Test
    public void employeeInstantiatesCorrectly_true(){
        Employee newEmployee = testEmployee();
        assertTrue(newEmployee instanceof Employee);
    }

    @Test
    public void getEmployeeNameReturnsEmployeeName_two(){
        Employee newEmployee = testEmployee();
        assertEquals("two", newEmployee.getEmployeeName());
    }

    @Test
    public void getPositionReturnsPosition(){
        String position = testEmployee().getEmployeePosition();
        assertEquals(position, testEmployee().getEmployeePosition());
    }

    @Test
    public void getDeptIdReturnsIdOfEmployeesDepartment_1(){
        Employee newEmployee = testEmployee();
        assertEquals(1, newEmployee.getDeptId());
    }

    @Test
    public void setterMethodsWorkCorrectlyInSettingNewValues(){
        Employee newEmployee = testEmployee();
        newEmployee.setEmployeeName("one");
        newEmployee.setEmployeePosition("mid-level");
        newEmployee.setDeptId(2);
        assertEquals("one", newEmployee.getEmployeeName());
        assertEquals("mid-level", newEmployee.getEmployeePosition());
        assertEquals(2, newEmployee.getDeptId());
    }
}