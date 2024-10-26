package luckyhunter.university.repository;

import lombok.extern.slf4j.Slf4j;
import luckyhunter.university.entity.Group;
import luckyhunter.university.entity.Student;
import luckyhunter.university.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с группами студентов в базе данных.
 */
@Slf4j
public class GroupRepository {

    public static final String GROUP_QUERY = "SELECT * " +
                        "FROM groups " +
                        "WHERE id = ?";
    public static final String STUDENT_QUERY = "SELECT s.* " +
                          "FROM student_group sg " +
                          "JOIN students s " +
                          "ON sg.student_id = s.id " +
                          "WHERE sg.group_id = ?";

    /**
     * Возвращает объект группы по указанному идентификатору вместе со списком студентов.
     *
     * @param groupId Идентификатор группы
     * @return Объект группы с заполненным списком студентов
     */
    public Group getGroupWithStudentsById(int groupId) {
        Group group = null;
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement groupStatement = connection.prepareStatement(GROUP_QUERY);
             PreparedStatement studentStatement = connection.prepareStatement(STUDENT_QUERY)) {

            groupStatement.setInt(1, groupId);
            ResultSet groupResultSet = groupStatement.executeQuery();
            if (groupResultSet.next()) {
                group = mapResultSetToGroup(groupResultSet);
            }

            studentStatement.setInt(1, groupId);
            ResultSet studentResultSet = studentStatement.executeQuery();
            while (studentResultSet.next()) {
                students.add(mapResultSetToStudent(studentResultSet));
            }

            if (group != null) {
                group.setStudents(students);
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return group;
    }

    /**
     * Возвращает список всех групп студентов из базы данных.
     *
     * @return Список объектов групп
     */
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM groups";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Group group = mapResultSetToGroup(resultSet);
                groups.add(group);
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return groups;
    }

    /**
     * Преобразует результат запроса базы данных в объект типа Group.
     *
     * @param resultSet Результат SQL-запроса
     * @return Объект группы
     * @throws SQLException Если возникла ошибка при чтении из ResultSet
     */
    private Group mapResultSetToGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setName(resultSet.getString("group_name"));
        return group;
    }

    /**
     * Преобразует результат запроса базы данных в объект типа Student.
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