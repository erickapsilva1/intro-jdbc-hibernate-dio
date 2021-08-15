package query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fs;

    @OneToMany(
            mappedBy = "name",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Student> students = new ArrayList<>();

    public State(int id, String name, String fs) {
        this.id = id;
        this.name = name;
        this.fs = fs;
    }

    public State(String name, String fs) {
        this.name = name;
        this.fs = fs;
    }

    public State() {
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

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id == state.id && Objects.equals(name, state.name) && Objects.equals(fs, state.fs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fs);
    }

    @Override
    public String toString() {
        return "jpa.State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uf='" + fs + '\'' +
                '}';
    }
}
