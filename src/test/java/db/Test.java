import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    // online mongodb
    /*
    MongoClientURI uri = new MongoClientURI("mongodb+srv:// + adminUsername + ":" + adminPassword + "@cluster0.bdpwk.mongodb.net/Cluster0?retryWrites=true&w=majority");
    MongoClient mongoClient;
    mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("test");
    */

    public static void main(String[] args){
        // variables
        protected User user = new User();

        // choosing login or registration
        actions(user);
    }
    protected static void actions(User user){
        protected boolean ret = false;
        do{
            protected AtomicInteger loginSetup = new AtomicInteger(Setup.login());
            if (loginSetup.get() == 0) {
                // login
                ret = Login.getData(user);
            } else if(loginSetup.get() == 1){
                // registration
                ret = Registration.addData(user);
            } else{
                continue;
            }
        }while(!ret);

        // commands after entering the page
        userActions(user);
    }
    protected static void userActions(User user){
        protected AtomicInteger commandSetup = new AtomicInteger(Setup.nextCommand());
        while(commandSetup.get() != 0){
            switch(commandSetup.get()){
                case 1 -> {
                    new DisplayData();
                }
                case 2 -> {
                    AlterUser.alterUser(user);
                }
                case 3 -> {
                    if(RemoveUser.removeUser(user)) actions(user);
                }
            }
            commandSetup.set(Setup.nextCommand());
        }
    }
}