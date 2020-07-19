package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    News testNews(){
        return new News("one", "12345");
    }

    @Test
    public void newsInstantiatesCorectly_true(){
        News news1 = testNews();
        assertTrue(news1 instanceof News);
    }

    @Test
    public void newsGetsTopicCorrectly_one(){
        News news1 = testNews();
        assertEquals("one", news1.getTopic());
    }

    @Test
    public void newsGetsContentCorectly_12345(){
        News news1 = testNews();
        assertEquals("12345", news1.getContent());
    }

    @Test
    public void settersWorkCorrectlyInSettingNewValues(){
        News news1 = testNews();
        news1.setTopic("two");
        news1.setContent("6789");
        assertEquals("two", news1.getTopic());
        assertEquals("6789", news1.getContent());
    }
}