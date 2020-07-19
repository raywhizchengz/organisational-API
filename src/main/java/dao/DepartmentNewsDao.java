package dao;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    //create
    void addDepartmental(DepartmentNews departmentNews);

    //read
    List<DepartmentNews> getAllDepartmental();

    // READ
    DepartmentNews findDepartmentalById(int id);

//    //update
//    void updateDepartmental(int id, String topic, String content, int deptId);

    //delete
    void deleteById(int id);
    void clearAllNews();
}
