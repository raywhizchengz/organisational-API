package dao;

import models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentNewsDao(Sql2o sql2o) {

        this.sql2o = sql2o;
    }

        @Override
    public void addDepartmental(DepartmentNews departmentNews) {
        String sql = "INSERT INTO departmentNews (topic, content, deptId) VALUES (:topic, :content, :deptId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(departmentNews)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            departmentNews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

        @Override
    public List<DepartmentNews> getAllDepartmental() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departmentNews")
                    .executeAndFetch(DepartmentNews.class);
        }
    }

        @Override
    public DepartmentNews findDepartmentalById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departmentNews WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departmentNews WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllNews() {
        String sql = "DELETE from departmentNews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
