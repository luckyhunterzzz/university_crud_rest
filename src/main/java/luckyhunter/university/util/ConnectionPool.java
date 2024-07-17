package luckyhunter.university.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Пул подключений к базе данных, использующий HikariCP для управления соединениями.
 */
public class ConnectionPool {
    private static HikariDataSource dataSource;

    /**
     * Статический блок инициализации, который настраивает и создает экземпляр HikariDataSource
     * для управления пулом соединений с базой данных PostgreSQL.
     */
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("luckyhunter");
        config.setPassword("luckyhunter");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    /**
     * Получение соединения из пула.
     *
     * @return Соединение с базой данных
     * @throws SQLException Исключение, возникающее при ошибке получения соединения
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
