import java.sql.*;

public class AddData {

    public AddData(String username, String password, String databaseName, String tableName){
        String addedUsername = Setup.username();
        String addedPassword = Setup.password();
        String addedApiKey = Setup.apiKey();
        String addedApiSecret = Setup.apiSecret();

        final String addUser = "INSERT INTO " + databaseName + "." + tableName + "(USERNAME, PASS, API_KEY, API_SECRET)\n" +
                                "VALUES ('" + addedUsername + "', '" + addedPassword + "', '" + addedApiKey + "', '" + addedApiSecret  + "');";

        try (Connection conn = DriverManager.getConnection(Setup.connectionUrl(databaseName), username, password);
             PreparedStatement ps = conn.prepareStatement(addUser);
             /*ps.executeUpdate()*/){
            System.out.println("User successfully added!");
        } catch (SQLException e){
            System.out.println(e);
            System.out.println("Error");
        }
    }

}
