package bank;
import static bank.DesignInterface.prt;
import java.util.Scanner;

public class AccountCreate {
    static Scanner scan = new Scanner(System.in);
    
    //if user answers NO on having an account
    public static AccountDetails basicInformation(){
        prt("---------------Create an Account---------------");
        prt("Name (Last Name, First Name):");   
        String name = scan.nextLine();
        String number = "";
        while (true) {
            prt("Phone Number:");
            number = scan.nextLine();
            
            if (number.matches("\\d+")) {
                break;
            } else {
                prt("Invalid phone number! Please enter only numbers.");
            }
        }
        String pin = createPin();

        return new AccountDetails(name, number, pin, 0);
    }

    //ask user to create their 4 digit pin
    public static String createPin(){
            prt("Create Pin (4-digits): ");   
            String a = scan.nextLine();

            if (a.matches("\\d{4}")){
                prt("Pin has been created: " + a);
                return a;
            } else {
                prt("Please input only digits (Max: 4)");
                return createPin();
            }
    }
}
