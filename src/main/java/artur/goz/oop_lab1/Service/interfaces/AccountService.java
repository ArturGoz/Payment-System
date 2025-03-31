package artur.goz.oop_lab1.Service.interfaces;

import artur.goz.oop_lab1.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccountsByUserId(int userId);
    void blockAccount(int accountId);
    void unblockAccount(int accountId);
}
