package db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NewSession {
    //public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
    //    new NewSession();
    //}
    public NewSession() throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = new User();
        loginOrRegister(user);
    }
    protected static void loginOrRegister(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AtomicBoolean looping = new AtomicBoolean(false);

        do{
            AtomicInteger loginSetup = new AtomicInteger(Setup.login());
            if (loginSetup.get() == 0) looping.set(Login.getData(user));
            else if(loginSetup.get() == 1) looping.set(Registration.addData(user));
        }while(!looping.get());

        userActions(user);
    }
    protected static void userActions(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AtomicInteger commandSetup = new AtomicInteger(Setup.nextCommand());

        while(commandSetup.get() != 0){
            switch(commandSetup.get()){
                case 1:
                    DisplayData.displayData();
                    break;
                case 2:
                    AlterUser.alterUser(user);
                    break;
                case 3:
                    if(RemoveUser.removeUser(user)) loginOrRegister(user);
                    break;
            }
            commandSetup.set(Setup.nextCommand());
        }
    }
}