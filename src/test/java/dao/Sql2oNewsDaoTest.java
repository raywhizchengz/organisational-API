package dao;

import models.DepartmentNews;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Sql2oNewsDao newsDao;
    private static Connection conn;

//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        newsDao = new Sql2oNewsDao(sql2o);
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
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        newsDao.clearAllNews();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    public News generalNews(){
        return new News("one", "twenty");
    }

    public DepartmentNews deptNews1(){
        return new DepartmentNews("two", "ten", 1);
    }

    @Test
    public void whenNewsIsAddedDatabaseSetsId() throws Exception{
        News news = generalNews();
        int generalId = news.getId();
        newsDao.addGeneral(news);
        assertNotEquals(generalId, news.getId());
    }

    @Test
    public void newsCanBeFoundById() throws Exception{
        News news = generalNews();
        newsDao.addGeneral(news);
        News foundNews = newsDao.findGeneralById(news.getId());
        assertEquals(news, foundNews);
    }

    @Test
    public void getAllNewsReturnsGeneralNews() throws Exception{
        News news = generalNews();
        News news1 = new News("1234","4567");
        newsDao.addGeneral(news);
        newsDao.addGeneral(news1);
        assertEquals(2, newsDao.getAllGeneral().size());
    }

    @Test
    public void ifNoNewsIsAvailableReturnsZero() throws Exception{
        assertEquals(0, newsDao.getAllGeneral().size());
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
        News news = generalNews();
        newsDao.addGeneral(news);
        newsDao.deleteById(news.getId());
        assertEquals(0, newsDao.getAllGeneral().size());
    }

    @Test
    public void deleteAllClearsAllNews() throws Exception{
        News news = generalNews();
        newsDao.addGeneral(news);
        newsDao.clearAllNews();
        assertEquals(0, newsDao.getAllGeneral().size());
    }
}