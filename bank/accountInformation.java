package bank;
import java.util.Scanner;
import java.util.Random;

public class accountInformation {
    static Scanner scan = new Scanner(System.in);
    static String[] accounts;

    //method for the account arrays
    public static void accountList(){
        accounts = new String[49];
        
    }   

    //assign random account ID
    public static void assignID() {
        Random rnd = new Random();
        int accountIdNumber = 10000 + rnd.nextInt(90000);
        System.out.println(accountIdNumber);
    }


}
