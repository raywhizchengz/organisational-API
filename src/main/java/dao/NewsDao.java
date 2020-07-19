package dao;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {
    //create
    void addGeneral(News news);

    //read
    List<News> getAllGeneral();

    // READ
    News findGeneralById(int id);

//    //update
//    void updateGeneral(int id, String topic, String content);
//    void updateDepartmental(int id, String topic, String content, int deptId);

    //delete
    void deleteById(int id);
    void clearAllNews();
}
