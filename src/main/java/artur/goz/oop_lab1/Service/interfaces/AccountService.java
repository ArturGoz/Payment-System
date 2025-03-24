package artur.goz.oop_lab1.Service.interfaces;

import artur.goz.oop_lab1.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getUserAccounts(int clientId);
    void blockAccount(int accountId);
    void unblockAccount(int accountId);
    void addFunds(int accountId, double amount);
}
