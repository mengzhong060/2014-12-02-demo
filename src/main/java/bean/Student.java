package bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author: wenchengzhu Date: 14-11-19 Time: 下午9:16
 * @version: $Id$
 */
@JsonSerialize
public class Student {
    long id;
    int studentId;

    public Student() {
    }

    public Student(long id, int studentId, String name, int age) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    String name;
    int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
