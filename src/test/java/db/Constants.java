package db;

public class Constants {
    public static final String adminUsername = "";
    public static final String adminPassword = "";
    public static final String databaseName = "sCryptoUsers";
    public static final String serverName = "scryptousers.database.windows.net";
    public static final String databaseUrl = "jdbc:sqlserver://" + serverName + ":1433;database=" + databaseName + ";user=" + adminUsername + "@scryptousers;password=" + adminPassword + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static final String tableName = "aUser";
    public static final String saltString = "";
    public static final byte[] salt = saltString.getBytes(); //PasswordEncryptionService.generateSalt();
}
