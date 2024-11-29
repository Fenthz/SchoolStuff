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

    // Unique ID (7-digit)
    private String generateAccountId() {
        Random rnd = new Random();
        String accountId;
        do {
            accountId = String.valueOf(1000000 + rnd.nextInt(9000000));
        } while (accounts.containsKey(accountId)); // check if ga exist naba
        return accountId;
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