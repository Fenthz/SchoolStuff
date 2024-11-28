package bank;
import java.util.Scanner;
import java.util.Random;

public class accountList {
    static Scanner scan = new Scanner(System.in);

    public static void assignID() {
        Random rnd = new Random();
        int accountIdNumber = 10000 + rnd.nextInt(90000);
        System.out.println(accountIdNumber);
    }


}
