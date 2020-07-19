package dao;

import models.Employee;
import org.sql2o.*;
import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao{
    private final Sql2o sql2o;

    public Sql2oEmployeeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Employee employee){
        String sql = "INSERT INTO employees (employeeName, employeePosition, deptId) VALUES (:employeeName, :employeePosition,:deptId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate()
                    .getKey();
            employee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Employee> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM employees")
                    .executeAndFetch(Employee.class);
        }
    }

    @Override
    public Employee findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM employees WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Employee.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from employees WHERE id =:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllEmployees() {
        String sql = "DELETE from employees";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
