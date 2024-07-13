package luckyhunter.university.repository;

import luckyhunter.university.entity.Subject;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeacherRepository {
    public Teacher getTeacherById(int id) {
        String query = "SELECT t.id, t.teacher_first_name, t.teacher_last_name, t.stage, ts.subject_id, s.subject_name " +
                "FROM teachers t " +
                "LEFT JOIN teacher_subjects ts ON t.id = ts.teacher_id " +
                "LEFT JOIN subjects s ON ts.subject_id = s.id " +
                "WHERE t.id = ?";

        Teacher teacher = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
            System.out.println("Got SQLException " + e.getMessage());
        }
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        String query = "SELECT t.id, t.teacher_first_name, t.teacher_last_name, t.stage, ts.subject_id, s.subject_name " +
                "FROM teachers t " +
                "LEFT JOIN teacher_subjects ts ON t.id = ts.teacher_id " +
                "LEFT JOIN subjects s ON ts.subject_id = s.id";

        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
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
            System.out.println("Got SQLException " + e.getMessage());
        }

        return teachers;
    }

}