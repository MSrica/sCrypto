import java.sql.*;

public class DisplayData {

    public DisplayData(String username, String password, String databaseName, String tableName){

        final String sqlSelectAllData = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(Setup.connectionUrl(databaseName), username, password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllData);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("Successfully connected!");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("USERNAME");
                String lastName = rs.getString("PASS");
                String apiKey = rs.getString("API_KEY");
                String apiSecret = rs.getString("API_SECRET");
                System.out.println(id + " " + name + " " + lastName + " " + apiKey + " " + apiSecret);
            }
        } catch (SQLException e){
            System.out.println("Error");
        }
    }

}
