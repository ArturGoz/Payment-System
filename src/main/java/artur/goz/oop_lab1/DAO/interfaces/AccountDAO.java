package artur.goz.oop_lab1.DAO.interfaces;

import artur.goz.oop_lab1.models.Account;

public interface AccountDAO {
    void blockAccount(int accountId, boolean block);

    Account getAccount(int accountId);

    void updateBalance(int accountId, double amount);

    Account createAccount(Account account);
}
