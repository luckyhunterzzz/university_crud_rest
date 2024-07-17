package luckyhunter.university.repository;

import luckyhunter.university.entity.Group;
import luckyhunter.university.entity.Schedule;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleRepository {

    public static final String QUERY_GET_ALL = "SELECT sc.id, sc.date, g.id AS group_id, g.group_name, " +
                   "t.id AS teacher_id, t.teacher_first_name, t.teacher_last_name, " +
                   "s.id AS subject_id, s.subject_name " +
                   "FROM schedule sc " +
                   "JOIN groups g ON sc.group_id = g.id " +
                   "JOIN teachers t ON sc.teacher_id = t.id " +
                   "JOIN subjects s ON sc.subject_id = s.id";

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                try {
                    schedule.setId(resultSet.getInt("id"));
                    schedule.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                    schedule.setGroup(new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")));
                    schedule.setTeacher(new Teacher(
                            resultSet.getInt("teacher_id"),
                            resultSet.getString("teacher_first_name"),
                            resultSet.getString("teacher_last_name"),
                            0,
                            null));
                    schedule.setSubject(new Subject(resultSet.getInt("subject_id"), resultSet.getString("subject_name")));
                    schedules.add(schedule);
                } catch (RuntimeException e) {
                    System.out.println("Got RuntimeException " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println("Got SQLException " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
        schedules.sort(Comparator.comparing(Schedule::getDate));
        return schedules;
    }
}