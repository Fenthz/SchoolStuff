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
                
        } else if ("2".equalsIgnoreCase(decision)){
            prt("Do you want to create an account?" + "\n[1] Yes | [2] No");
            String a = scan.nextLine();

                if ("1".equalsIgnoreCase(a)){
                    accountCreation();
                } else if ("2".equalsIgnoreCase(a)){
                    welcomeScreen();
                }
            
        }
    }


    //if user answers NO on having an account
    public static void accountCreation(){
        prt("---------------Create an Account---------------");
        prt("Name (Last Name, First Name):");   
        scan.nextLine();
            try{
        prt("Phone Number: ");   
        scan.nextLine();
            }catch (Exception e){
            prt("Please provide a number");
        }
        createPin();

    }

    public static void createPin(){
            prt("Create Pin (4-digits): ");   
            String a = scan.nextLine();

            if (a.matches("\\d{4}")){
                int b = Integer.parseInt(a);
                prt("Pin has been created: " + a);
            } else {
                prt("Please input only digits (Max: 4)");
                createPin();
            }
    }

    //shortens the sys.out command
    public static void prt(String a){
        System.out.println(a);
    }
}