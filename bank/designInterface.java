package bank;
import java.util.Scanner;

public class designInterface {
    static Scanner scan = new Scanner(System.in);

    public static void loginScreen() {
        prt("---------------LOGIN MENU---------------");
        prt("why");
    }

    //shortens the sys.out command
    public static void prt(String a){
        System.out.println(a);
    }
}
