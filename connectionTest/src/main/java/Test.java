import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    // online mongodb
    /*
    MongoClientURI uri = new MongoClientURI("mongodb+srv://root:mongodbkarmat67@cluster0.bdpwk.mongodb.net/Cluster0?retryWrites=true&w=majority");
    MongoClient mongoClient;
    mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("test");
    */

    public static void main(String[] args){
        String databaseName = "people"; //Setup.databaseName();
        String tableName =  "person";   //Setup.tableName();

        System.out.println("Log in");
        String username = Setup.username();
        String password = Setup.password();

        AtomicInteger commandSetup = new AtomicInteger(Setup.nextCommand());
        while(commandSetup.get() != 0){
            if(commandSetup.get() == 1) {
                //new AddData(username, password, databaseName, tableName);
                System.out.println("Not implemented yet!");
            }else{
                new DisplayData(username, password, databaseName, tableName);
            }
            commandSetup.set(Setup.nextCommand());
        }
    }
}
