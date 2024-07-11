package luckyhunter.university.repository;

import luckyhunter.university.entity.Student;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
        return students;
    }

    public Student getStudentById(int id) {
        Student student = null;
        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = mapResultSetToStudent(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
        return student;
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE first_name = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
        return students;
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (first_name, last_name, birth_date, phone_number) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDate(3, java.sql.Date.valueOf(student.getBirthDate()));
            statement.setString(4, student.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
    }

    public void deleteStudentById(int id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
    }

    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        student.setPhoneNumber(resultSet.getString("phone_number"));
        return student;
    }
}
