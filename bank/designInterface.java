package bank;
import java.util.Scanner;

public class DesignInterface {
    static Scanner scan = new Scanner(System.in);
    static AccountManager accountManager = new AccountManager();

    //first screen the user sees
    public static void welcomeScreen() {
        try {
        prt("---------------Welcome to FTL Bank---------------");
        prt("Do you have an account?" + "\n[1] Yes | [2] No");
        int decision = Integer.parseInt(scan.nextLine());

        switch (decision){
        case 1: //yes
            loginScreen();
            break;
        case 2: //no
            while (true){
                try{
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
                }catch(NumberFormatException e){
                    wrongDec("1 or 2");
               }

            }
        default:
            wrongDec("1 or 2");
            welcomeScreen();
        }
        } catch (NumberFormatException e) {
            wrongDec("1 or 2");
            welcomeScreen();
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
        
        try {
            if (!accountManager.accountExists(accountId)) {
                prt("Account not found. Try again?" + "\n [1] Yes | [2] No");
                String decision = scan.nextLine();
                switch (decision){
                    case "1": 
                        loginScreen();
                    case "2": 
                        welcomeScreen();
                    default:
                        wrongDec("the correct numbers");  
                }
            }else {
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
                }
            }
        } 
        }catch (Exception e) {
            prt("Please enter a pin number");
        }
    }
}              

    public static void accountMenu(AccountDetails account) {
        int decision;
        prt("---------------Welcome to FTL Bank---------------" + "\n---------------Hello, " + account.getName() + "---------------" + "\n---------------What do you want to do today?---------------");
        do {
            prt("[1] Deposit" + "\n[2] Withdraw"+ "\n[3] Check Balance"+ "\n[4] Exit/Logout");
            decision = Integer.parseInt(scan.nextLine());

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
            case 4: // Exit
                prt("Thank you for banking with us!");
                prt("Do you wish to login again?" + "\n[1] Yes | [2] No");
                String a = scan.nextLine();
                    if ("1".equalsIgnoreCase(a)){
                        welcomeScreen();
                    } else{
                        break; 
                    }
            default:
                wrongDec("the correct numbers");
    }
        }while (decision != 4);
            //much empty 
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