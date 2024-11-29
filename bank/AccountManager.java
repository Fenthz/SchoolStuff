package bank;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountManager {
    private Map<String, AccountDetails> accounts = new HashMap<>(); //account storage

    //make new acc
    public String createAccount(String name, String number, String pin, int balance) {
        String accountId = generateAccountId(); 
        AccountDetails account = new AccountDetails(name, number, pin, balance);
        accounts.put(accountId, account); 
        return accountId; 
    }

    //Unique ID (7-digit)
    private String generateAccountId() {
        Random rnd = new Random();
        String accountId;
        do {
            accountId = String.valueOf(1000000 + rnd.nextInt(9000000));
        } while (accounts.containsKey(accountId)); // check if ga exist naba
        return accountId;
        
    }

    //pang update sa accounts. Takes the current User's ID (from accounts) and then individually takes specific information
    public boolean updateAccount(String accountId, String name, String number, String pin) {
        if (accounts.containsKey(accountId)) {
            AccountDetails account = accounts.get(accountId);
            if (name != null) account.name = name;
            if (number != null) account.number = number;
            if (pin != null) account.pin = pin;
            return true;
        }
        return false;
    }

    //check if naay account
    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    //get account details
    public AccountDetails getAccount(String accountId) {
        return accounts.get(accountId);
    }
}