package models;

import java.util.Objects;

public class Department {
    private String deptName;
    private String description;
    private int noOfEmployees;
    private int id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return noOfEmployees == that.noOfEmployees &&
                id == that.id &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptName, description, noOfEmployees, id);
    }

    public Department(String deptName, String description, int noOfEmployees){
        this.deptName = deptName;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
