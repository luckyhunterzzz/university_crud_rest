package luckyhunter.university.repository;

import lombok.extern.slf4j.Slf4j;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Репозиторий для работы с преподавателями в базе данных.
 */
@Slf4j
public class TeacherRepository {

    public static final String QUERY_BY_ID = "SELECT t.id, t.teacher_first_name, t.teacher_last_name, t.stage, ts.subject_id, s.subject_name " +
            "FROM teachers t " +
            "LEFT JOIN teacher_subjects ts ON t.id = ts.teacher_id " +
            "LEFT JOIN subjects s ON ts.subject_id = s.id " +
            "WHERE t.id = ?";
    public static final String QUERY_ALL_TEACHERS = "SELECT t.id, t.teacher_first_name, t.teacher_last_name, t.stage, ts.subject_id, s.subject_name " +
            "FROM teachers t " +
            "LEFT JOIN teacher_subjects ts ON t.id = ts.teacher_id " +
            "LEFT JOIN subjects s ON ts.subject_id = s.id";

    /**
     * Возвращает объект преподавателя по указанному идентификатору вместе с перечислением предметов, которые он ведет.
     *
     * @param id Идентификатор преподавателя
     * @return Объект преподавателя с заполненным списком предметов
     */
    public Teacher getTeacherById(int id) {

        Teacher teacher = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (teacher == null) {
                    teacher = new Teacher();
                    teacher.setId(resultSet.getInt("id"));
                    teacher.setTeacherFirstName(resultSet.getString("teacher_first_name"));
                    teacher.setTeacherLastName(resultSet.getString("teacher_last_name"));
                    teacher.setStage(resultSet.getInt("stage"));
                    teacher.setSubjects(new ArrayList<>());
                }

                int subjectId = resultSet.getInt("subject_id");
                String subjectName = resultSet.getString("subject_name");
                if (subjectId > 0 && subjectName != null) {
                    teacher.getSubjects().add(new Subject(subjectId, subjectName));
                }
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return teacher;
    }

    /**
     * Возвращает список всех преподавателей из базы данных вместе с перечислением предметов,
     * которые они ведут.
     *
     * @return Список объектов преподавателей с заполненными списками предметов
     */
    public List<Teacher> getAllTeachers() {

        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_ALL_TEACHERS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int teacherId = resultSet.getInt("id");
                Optional<Teacher> existingTeacherOpt = teachers.stream()
                        .filter(t -> t.getId() == teacherId)
                        .findFirst();

                if (existingTeacherOpt.isPresent()) {
                    Teacher existingTeacher = existingTeacherOpt.get();
                    int subjectId = resultSet.getInt("subject_id");
                    String subjectName = resultSet.getString("subject_name");
                    if (subjectId > 0 && subjectName != null) {
                        existingTeacher.getSubjects().add(new Subject(subjectId, subjectName));
                    }
                } else {
                    Teacher newTeacher = new Teacher();
                    newTeacher.setId(teacherId);
                    newTeacher.setTeacherFirstName(resultSet.getString("teacher_first_name"));
                    newTeacher.setTeacherLastName(resultSet.getString("teacher_last_name"));
                    newTeacher.setStage(resultSet.getInt("stage"));
                    newTeacher.setSubjects(new ArrayList<>());

                    int subjectId = resultSet.getInt("subject_id");
                    String subjectName = resultSet.getString("subject_name");
                    if (subjectId > 0 && subjectName != null) {
                        newTeacher.getSubjects().add(new Subject(subjectId, subjectName));
                    }

                    teachers.add(newTeacher);
                }
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }

        return teachers;
    }

}