package luckyhunter.university.repository;

import lombok.extern.slf4j.Slf4j;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с предметами в базе данных.
 */
@Slf4j
public class SubjectRepository {

    public static final String QUERY_GET_ALL_SUBJECTS = "SELECT * FROM subjects";

    /**
     * Возвращает список всех предметов из базы данных.
     *
     * @return Список объектов предметов
     */
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_SUBJECTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                subjects.add(new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("subject_name")));
            }
        } catch (SQLException e) {
            log.error("Got SQLException " + e.getMessage());
        }
        return subjects;
    }
}
