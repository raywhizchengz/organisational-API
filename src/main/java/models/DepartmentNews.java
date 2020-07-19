package models;

import java.util.Objects;

public class DepartmentNews{
    private String topic;
    private String content;
    private int deptId;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentNews that = (DepartmentNews) o;
        return deptId == that.deptId &&
                id == that.id &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, content, deptId, id);
    }

    public DepartmentNews(String topic, String content, int deptId) {
        this.topic = topic;
        this.content = content;
        this.deptId = deptId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
