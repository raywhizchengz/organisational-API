package dao;

import models.DepartmentNews;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {
    private static Sql2oDepartmentNewsDao departmentNewsDao;
    private static Connection conn;

//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
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
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        departmentNewsDao.clearAllNews();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    public DepartmentNews departmentNews(){
        return new DepartmentNews("one", "twenty", 2);
    }

    @Test
    public void whenDepartmentNewsIsAddedDatabaseSetsId() throws Exception{
        DepartmentNews departmentNews = departmentNews();
        int deptNewsId = departmentNews.getId();
        departmentNewsDao.addDepartmental(departmentNews);
        assertNotEquals(deptNewsId, departmentNews.getId());
    }

    @Test
    public void departmentNewsCanBeFoundById() throws Exception{
        DepartmentNews departmentNews = departmentNews();
        departmentNewsDao.addDepartmental(departmentNews);
        DepartmentNews foundNews = departmentNewsDao.findDepartmentalById(departmentNews.getId());
        assertEquals(departmentNews, foundNews);
    }

    @Test
    public void getAllNewsReturnsDepartmentNews() throws Exception{
        DepartmentNews departmentNews = departmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("1234","4567", 4);
        departmentNewsDao.addDepartmental(departmentNews);
        departmentNewsDao.addDepartmental(departmentNews1);
        assertEquals(2, departmentNewsDao.getAllDepartmental().size());
    }

    @Test
    public void ifNoNewsIsAvailableReturnsZero() throws Exception{
        assertEquals(0, departmentNewsDao.getAllDepartmental().size());
    }

//    @Test
//    public void updateChangesNewsInformation() throws Exception{
//        News news = generalNews();
//        DepartmentNews departmentNews = deptNews1();
//        newsDao.addGeneral(news);
//        newsDao.addDepartmental(departmentNews);
//        newsDao.updateGeneral(news.getId(), "arrival", "come early");
//        newsDao.updateDepartmental(departmentNews.getId(), "departure", "leave late", 2);
//        News news1 = newsDao.findGeneralById(news.getId());
//        DepartmentNews dept1 = newsDao.findDepartmentalById(departmentNews.getId());
//        assertNotEquals(news.getTopic(), news1.getTopic());
//        assertNotEquals(news.getContent(), news1.getContent());
//        assertNotEquals(departmentNews.getContent(), dept1.getContent());
//        assertNotEquals(departmentNews.getTopic(), dept1.getTopic());
//        assertNotEquals(departmentNews.getDeptId(), dept1.getDeptId());
//    }

    @Test
    public void deleteByIdDeletesSingleNews() throws Exception {
        DepartmentNews departmentNews = departmentNews();
        departmentNewsDao.addDepartmental(departmentNews);
        departmentNewsDao.deleteById(departmentNews.getId());
        assertEquals(0, departmentNewsDao.getAllDepartmental().size());
    }

    @Test
    public void deleteAllClearsAllNews() throws Exception{
        DepartmentNews departmentNews = departmentNews();
        departmentNewsDao.addDepartmental(departmentNews);
        departmentNewsDao.clearAllNews();
        assertEquals(0, departmentNewsDao.getAllDepartmental().size());
    }
}