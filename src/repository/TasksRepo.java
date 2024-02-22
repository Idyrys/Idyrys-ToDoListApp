package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TasksRepo {
    private final String url = "jdbc:postgresql://localhost:5432/to_do_list_app";
    private final String user = "postgres";
    private final String password = "12345";

    public Connection connectToDataBase() {

        Connection connection = null;
        {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Ошибка при соединение56789 ! "+e.getMessage());
            }
        }
        return connection;
    }
}
