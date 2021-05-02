import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Setup {

    static Scanner scn = new Scanner(System.in);

    protected static String username(){
        System.out.println("Please enter username");
        return scn.next();
    }
    protected static String password(){
        System.out.println("Please enter password");
        return scn.next();
    }
    protected static String apiKey(){
        System.out.println("Please enter API key");
        return scn.next();
    }
    protected static String apiSecret(){
        System.out.println("Please enter API secret");
        return scn.next();
    }

    protected static String tableName(){
        System.out.println("Please enter table name");
        return scn.next();
    }
    protected static String databaseName(){
        System.out.println("Please enter database name");
        return scn.next();
    }

    protected static String connectionUrl(String databaseName){
        return "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=UTC";
    }

    protected static int nextCommand(){
        AtomicInteger ret = new AtomicInteger();
        System.out.println("Enter 0 for exit, 1 for adding, 2 for database display");

        while(true){
            try {
                ret.set(Integer.parseInt(scn.next()));
                break;
            }catch(Exception e){
                System.out.println("Please enter a number between 0 and 2");
            }
        }
        return ret.get();
    }
}
