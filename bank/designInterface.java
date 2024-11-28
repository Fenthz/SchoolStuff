package bank;
import java.util.Scanner;

public class designInterface {
    static Scanner scan = new Scanner(System.in);

    //first screen the user sees
    public static void welcomeScreen() {
        prt("---------------Welcome to FTL Bank---------------");
        prt("Do you have an account?" + "\n[1] Yes | [2] No");

        String decision = scan.nextLine();

            if ("1".equalsIgnoreCase(decision)){
                accountInformation();
            } else if ("2".equalsIgnoreCase(decision)){
                //go to create account screen
            }
    }

    //if user answers Yes on having an account
    public static void accountInformation(){
        prt("---------------Create an Account---------------");
    }

    //shortens the sys.out command
    public static void prt(String a){
        System.out.println(a);
    }
}
