package luckyhunter.university.repository;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    public List<Subject> getAllSubjects() {
        String query = "SELECT * FROM subjects";
        List<Subject> subjects = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                subjects.add(new Subject(resultSet.getInt("id"),
                        resultSet.getString("subject_name")));
            }
        } catch (SQLException e) {
            System.out.println("Got SQLException " + e.getMessage());
        }
        return subjects;
    }
}
