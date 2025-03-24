package artur.goz.oop_lab1.DAO.interfaces;

import artur.goz.oop_lab1.models.Account;

import java.util.List;

public interface AccountDAO {
    void blockAccount(int accountId, boolean block);
    void addFunds(int accountId, double amount);
    Account getAccount(int accountId);
    List<Account> getUserAccounts(int userId);
    void updateBalance(int accountId, double amount);
}
