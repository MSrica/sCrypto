package db;

import java.sql.*;

public class DisplayData {
    public static void displayData(){
        // displays entire database, testing purposes only!
        final String sqlSelectAllData = "SELECT * FROM " + Constants.tableName;
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllData);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String name = rs.getString("USERNAME");
                String pass = rs.getString("PASS");
                String email = rs.getString("EMAIL");
                String apiKey = rs.getString("API_KEY");
                String apiSecret = rs.getString("API_SECRET");
                String taapiKey = rs.getString("TAAPI_KEY");
                System.out.println(id + " " + name + " " + pass + " " + email + " " + apiKey + " " + apiSecret + " " + taapiKey);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
