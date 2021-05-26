package db;

import java.sql.*;

public class DisplayData {

    public DisplayData(){
        final String sqlSelectAllData = "SELECT * FROM " + Constants.tableName;
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllData);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("USERNAME");
                String pass = rs.getString("PASS");
                String email = rs.getString("EMAIL");
                String apiKey = rs.getString("API_KEY");
                String apiSecret = rs.getString("API_SECRET");
                System.out.println(name + " " + pass + " " + email + " " + apiKey + " " + apiSecret);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
