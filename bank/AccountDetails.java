package bank;

public class AccountDetails {
    String name;
    String pin;
    String number; 
    int balance;

    public AccountDetails(String name, String number, String pin, int balance) {
        this.name = name;
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }
     

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    } //kapoy nako
}
