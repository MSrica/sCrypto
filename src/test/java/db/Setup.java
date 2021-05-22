package db;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Setup {

    protected static Scanner scn = new Scanner(System.in);

    protected static String username(){
        System.out.println("Please enter username");
        return scn.next();
    }
    protected static String password(){
        System.out.println("Please enter password");
        return scn.next();
    }
    protected static String email(){
        System.out.println("Please enter email");
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
    protected static boolean doubleCheck(){
        System.out.println("Enter 0 to cancel, 1 to delete account");
        return scn.next().equals("1");
    }

    protected static String connectionUrl(){
        return "jdbc:mysql://localhost:3306/" + Constants.databaseName + "?serverTimezone=UTC";
    }

    protected static int nextCommand(){
        AtomicInteger ret = new AtomicInteger();
        System.out.println("Enter 0 for exit, 1 for database display, 2 for altering account, 3 for deleting account");

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
    protected static int login(){
        AtomicInteger ret = new AtomicInteger();
        System.out.println("Enter 0 for login, 1 for registration");

        while(true){
            try {
                ret.set(Integer.parseInt(scn.next()));
                break;
            }catch(Exception e){
                System.out.println("Please enter a number between 0 and 1");
            }
        }
        return ret.get();
    }
    protected static int whatToChange(){
        AtomicInteger ret = new AtomicInteger();
        System.out.println("Enter 0 for exit altering, 1 for username, 2 for password, 3 for email, 4 for API key or 5 for API secret");

        while(true){
            try {
                ret.set(Integer.parseInt(scn.next()));
                break;
            }catch(Exception e){
                System.out.println("Please enter a number between 0 and 3");
            }
        }
        return ret.get();
    }
}
