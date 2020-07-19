package models;

import java.util.Objects;

public class Employee {
    private String employeeName;
    private String employeePosition;
    private int deptId;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return deptId == employee.deptId &&
                id == employee.id &&
                Objects.equals(employeeName, employee.employeeName) &&
                Objects.equals(employeePosition, employee.employeePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, employeePosition, deptId, id);
    }

    public Employee(String employeeName, String employeePosition, int deptId){
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.deptId = deptId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
