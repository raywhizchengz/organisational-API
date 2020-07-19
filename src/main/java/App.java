import com.google.gson.Gson;
import dao.Sql2oDepartmentNewsDao;
import dao.Sql2oEmployeeDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import models.Department;
import models.DepartmentNews;
import models.Employee;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oEmployeeDao employeeDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oDepartmentNewsDao departmentNewsDao;

        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/organization.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        String connectionString = "jdbc:postgresql://localhost:5432/organizationapi";
//        Sql2o sql2o = new Sql2o(connectionString, "mringaschool", "12345");

        employeeDao = new Sql2oEmployeeDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();

//        post: create new department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

//        get: show all existing departments
        get("/departments", "application/json", (req, res) -> {
            return gson.toJson(departmentDao.getAll());
        });

//        get: show specific department by id
        get("/departments/:id", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findById(departmentId));
        });

//        post: add new employee
        post("/employees/new", "application/json", (req, res) -> {
            Employee employee = gson.fromJson(req.body(), Employee.class);
            employeeDao.add(employee);
            res.status(201);
            return gson.toJson(employee);
        });

//        get: show all employees regardless of department
        get("/employees", "application/json", (req, res)->{
            return gson.toJson(employeeDao.getAll());
        });

//        get: show all employees with a specific id
        get("/employees/:id", "application/json", (req, res) -> {
            int employeeId = Integer.parseInt(req.params("id"));
            return gson.toJson(employeeDao.findById(employeeId));
        });

//        filters
        after((req, res) ->{
            res.type("application/json");
        });

        //        get: show all employees in a specific department
        get("/department/:deptId", "application/json", (req, res) ->{
//            int deptId = Integer.parseInt("deptId");
            int deptId = Integer.parseInt(req.queryParams("deptId"));
            Employee employee = gson.fromJson(req.body(), Employee.class);

            employee.setDeptId(deptId);
            departmentDao.getAllEmployeesInADept(deptId);
            res.status(201);
            return gson.toJson(employee);
        });

//        post: create news
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.addGeneral(news);
            res.status(201);
            return gson.toJson(news);
        });

//        get: show all generalNews
        get("/news", "application/json", (req, res)->{
            return gson.toJson(newsDao.getAllGeneral());
        });

//        post: create departmentNews
        post("/departmentNews/new", "application/json", (req, res) ->{
            DepartmentNews departmentNews = gson.fromJson(req.body(), DepartmentNews.class);
            departmentNewsDao.addDepartmental(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });

//        get: show all departmentNews
        get("/departmentNews", "application/json", (req, res)->{
            return gson.toJson(departmentNewsDao.getAllDepartmental());
        });
    }
}