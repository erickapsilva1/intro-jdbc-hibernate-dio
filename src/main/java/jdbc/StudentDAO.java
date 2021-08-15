package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student>list() {

        List<Student> students = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "select * from STUDENT";

            PreparedStatement stms = conn.prepareStatement(sql);

            ResultSet rs = stms.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                String state = rs.getString("STATE");

                students.add(new Student(
                        id,
                        name,
                        age,
                        state
                ));
            }

        } catch (SQLException e) {
            System.out.println("jdbc.jpa.Student list failed.");
            e.printStackTrace();
        }
        return students;
    }

    public Student getById(int id){

        Student student = new Student();

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "select * from STUDENT where id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                student.setId(rs.getInt("ID"));
                student.setName(rs.getString("NAME"));
                student.setAge(rs.getInt("AGE"));
                student.setState(rs.getString("STATE"));
            }
        }catch(SQLException e){
            System.out.println("jdbc.jpa.Student list failed.");
            e.printStackTrace();
        }
        return student;
    }

    public void insert(Student student){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "insert into STUDENT(NAME, AGE, STATE) values (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getState());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Success! Rows affected: " + rowsAffected);

        }catch(SQLException e){
            System.out.println("Insert failed");
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "delete from STUDENT where id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Success! Rows affected: " + rowsAffected);

        }catch(SQLException e){
            System.out.println("Delete failed.");
            e.printStackTrace();
        }
    }

    public void update(Student student){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "update STUDENT set NAME = ?, AGE = ?, STATE = ? where ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getState());
            stmt.setInt(4, student.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Success! Rows affected: " + rowsAffected);

        }catch(SQLException e){
            System.out.println("Delete failed.");
        }
    }

}
