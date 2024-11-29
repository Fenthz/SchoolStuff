package bank;
import java.util.Scanner;

public class DesignInterface {
    static Scanner scan = new Scanner(System.in);
    static AccountManager accountManager = new AccountManager();

    //first screen the user sees
    public static void welcomeScreen() {
        prt("---------------Welcome to FTL Bank---------------");
        prt("Do you have an account?" + "\n[1] Yes | [2] No");
        while (true){
        try {
            int decision = Integer.parseInt(scan.nextLine());
        
        switch (decision){
        case 1: //yes
            loginScreen();
            break;
        case 2: //no
            while (true){
                prt("Do you want to create an account?" + "\n[1] Yes | [2] No");
                    int a = Integer.parseInt(scan.nextLine());
                        switch(a){
                        case 1:
                            makeAccountProc();
                            return;
                        case 2:
                            welcomeScreen();
                            return;
                        default:    
                            wrongDec("1 or 2");
                        }
            }
        default:
            wrongDec("1 or 2"); // If the input is not 1 or 2, show an error

            }
        } catch (NumberFormatException e) {
            wrongDec("1 or 2");

        }
    }
    }

      
    private static void makeAccountProc() {
     
        AccountDetails account = AccountCreate.basicInformation();   

        String accountId = accountManager.createAccount(account.getName(), account.getNumber(), account.getPin(), account.getBalance());
        
         prt("Account created successfully! Your account ID is: " + accountId);
         loginScreen();
        }
    

    
    public static void loginScreen(){
        int a = 0; //max number of attempts or counter

        while (a < 5){
        prt("Enter your account ID:");
        String accountId = scan.nextLine();

        AccountDetails account = accountManager.getAccount(accountId);
        
            if (!accountManager.accountExists(accountId)) {
                prt("Account not found. Try again?" + "\n [1] Yes | [2] No");
                String decision = scan.nextLine();

                if ("1".equalsIgnoreCase(decision)) {
                    continue; 
                } else if ("2".equalsIgnoreCase(decision)) {
                    welcomeScreen(); 
                    return;
                } else {
                    wrongDec("1 or 2");
                }
            } else {
            prt("Enter your pin:");
            String userPin = scan.nextLine();
    
                if (userPin.equalsIgnoreCase(account.getPin())) {
                    prt("Login Success");
                    accountMenu(account); 
                    break;
                }else {
                    a++;
                    prt("Incorrect pin. Please try again.");
                    if (a >= 5) {
                        prt("Too many failed attempts. Exiting...");
                        welcomeScreen(); //Exit after 5 failed attempts
                        return;
                }
            }
        } 
    }
}              

    public static void accountMenu(AccountDetails account) {

        int decision = 0; //somehow doesnt work if i dont assign value
        prt("---------------Welcome to FTL Bank---------------" + "\n---------------Hello, " + account.getName() + "---------------");
        do{
        prt("\n---------------What do you want to do today?---------------");
        prt("[1] Deposit" + "\n[2] Withdraw"+ "\n[3] Check Balance"+ "\n[4] Edit Account Details" + "\n[5] Exit/Logout");
        
            try {
                decision = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                prt("Invalid input! Please enter a number.");
                continue; 
            }
        
            switch (decision){
            case 1: // Deposit
                prt("Enter amount to deposit:");
                int depositAmount = Integer.parseInt(scan.nextLine());
                account.setBalance(account.getBalance() + depositAmount);
                prt("Deposit successful! Your new balance is: " + account.getBalance());

                break;
            case 2: 
                prt("Enter amount to withdraw:");
                    int withdrawAmount = Integer.parseInt(scan.nextLine());

                        if (withdrawAmount <= 0) {
                            prt("Please enter a positive amount.");
                            break;
                        }

                        if (withdrawAmount > account.getBalance()) {
                            prt("Insufficient funds. Your current balance is: " + account.getBalance());
                        } else {
                            account.setBalance(account.getBalance() - withdrawAmount);
                            prt("Withdrawal successful! Your new balance is: " + account.getBalance());
                        }
            
                break;
            case 3: 
                prt("Your current balance is: " + account.getBalance());
                break;
            case 4:
                prt("---------------Edit Account Details---------------");
                boolean editing = true;
                    
                    while (editing){
                        prt("Choose information to edit:" + "\n[1] Name" + "\n[2] Phone Number" + 
                        "\n[3] PIN" + "\n[4] Cancel");

                        int infoEdit;
                        try{
                            infoEdit = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            prt("Invalid input! Please enter a number.");
                            continue;
                        }
                        switch (infoEdit) {
                            case 1: 
                                prt("Enter new name (Last Name, First Name):");
                                String newName = scan.nextLine();
                                account.name = newName; // Update locally
                                accountManager.updateAccount(account.getNumber(), newName, account.number, account.pin);
                                prt("Name updated successfully!");
                                break;
                
                            case 2: 
                                prt("Enter new phone number:");
                                String newNumber = scan.nextLine();
                                account.number = newNumber; // Update locally
                                accountManager.updateAccount(account.getNumber(), account.name, newNumber, account.pin);
                                prt("Phone number updated successfully!");
                                break;
                
                            case 3: 
                                prt("Create a new PIN (4 digits):");
                                String newPin;
                                while (true) {
                                    newPin = scan.nextLine();
                                    if (newPin.matches("\\d{4}")) {
                                        break;
                                    } else {
                                        prt("Invalid PIN! Please enter exactly 4 digits.");
                                    }
                                }
                                account.pin = newPin;
                                accountManager.updateAccount(account.getNumber(), account.name, account.number, newPin);
                                prt("PIN updated successfully!");
                                break;
                
                            case 4:
                                prt("Returning to Account menu...");
                                editing = false;
                                break;
                
                            default:
                                prt("Invalid choice! Please select a valid option.");
                                break;
                    }
                break;
                }
            case 5: // Exit
                prt("Thank you for banking with us!");
                prt("Do you wish to login again?" + "\n[1] Yes | [2] No");

                while (true) {
                    String a = scan.nextLine();
                    if ("1".equalsIgnoreCase(a)) {
                        welcomeScreen();
                        return; 
                    } else if ("2".equalsIgnoreCase(a)) {
                        return;
                    } else {
                        wrongDec("the correct numbers");
                    }
                }

            default:
                wrongDec("the correct numbers");
    }
    }while(decision != 5);
}






    //lazy to type out the error messages
    public static void wrongDec(String a){
        prt("Invalid decision. Please enter " + a );
    }

    //shortens the sys.out command
    public static void prt(String a){
        System.out.println(a);
    }
}