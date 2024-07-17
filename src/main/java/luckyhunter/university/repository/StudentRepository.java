package luckyhunter.university.repository;

import lombok.extern.slf4j.Slf4j;
import luckyhunter.university.entity.Student;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с данными о студентах в базе данных.
 */
@Slf4j
public class StudentRepository {

    public static final String QUERY_GET_ALL = "SELECT * FROM students";
    public static final String QUERY_GET_BY_ID = "SELECT * FROM students WHERE id = ?";
    public static final String QUERY_GET_BY_FIRSTNAME = "SELECT * FROM students WHERE first_name = ?";
    public static final String QUERY_ADD = "INSERT INTO students (first_name, last_name, birth_date, phone_number) VALUES (?, ?, ?, ?)";
    public static final String QUERY_DELETE = "DELETE FROM students WHERE id = ?";

    /**
     * Возвращает список всех студентов из базы данных.
     *
     * @return Список объектов студентов
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return students;
    }

    /**
     * Возвращает объект студента по указанному идентификатору.
     *
     * @param id Идентификатор студента
     * @return Объект студента или null, если студент не найден
     */
    public Student getStudentById(int id) {
        Student student = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = mapResultSetToStudent(resultSet);
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return student;
    }

    /**
     * Возвращает список студентов с указанным именем.
     *
     * @param firstName Имя студента
     * @return Список объектов студентов с указанным именем
     */
    public List<Student> getStudentsByFirstName(String firstName) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_FIRSTNAME)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return students;
    }

    /**
     * Добавляет нового студента в базу данных.
     *
     * @param student Объект студента для добавления
     */
    public void addStudent(Student student) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDate(3, java.sql.Date.valueOf(student.getBirthDate()));
            statement.setString(4, student.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
    }

    /**
     * Удаляет студента из базы данных по указанному идентификатору.
     *
     * @param id Идентификатор студента для удаления
     */
    public void deleteStudentById(int id) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
    }

    /**
     * Преобразует результат SQL-запроса в объект типа Student.
     *
     * @param resultSet Результат SQL-запроса
     * @return Объект студента
     * @throws SQLException Если возникла ошибка при чтении из ResultSet
     */
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
