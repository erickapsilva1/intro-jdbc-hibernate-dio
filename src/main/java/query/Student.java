package query;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    private State state;

    public Student(int id, String name, int age, State state) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.state = state;
    }

    public Student(String name, int age, State state) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
        return "jpa.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", state=" + state +
                '}';
    }
}
