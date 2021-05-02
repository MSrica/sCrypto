import java.sql.*;

public class Connect {
    public static Connection returnConnection(String databaseName, String username, String password){
        try {
            Connection conn = DriverManager.getConnection(Setup.connectionUrl(databaseName), username, password);
            System.out.println("Connected to database successfully");
            return conn;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
