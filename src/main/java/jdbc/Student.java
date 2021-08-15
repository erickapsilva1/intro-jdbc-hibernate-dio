package jdbc;

import java.util.Objects;

public class Student {

    private int id;
    private String name;
    private int age;
    private String state;

    public Student(int id, String name, int age, String state) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.state = state;
    }

    public Student(String name, int age, String state) {
        this.name = name;
        this.age = age;
        this.state = state;
    }

    public Student(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(state, student.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, state);
    }

    @Override
    public String toString() {
        return "jdbc.jpa.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", state='" + state + '\'' +
                '}';
    }
}
