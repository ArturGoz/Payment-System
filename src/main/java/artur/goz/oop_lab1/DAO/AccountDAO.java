package artur.goz.oop_lab1.DAO;

public interface AccountDAO {
    void blockAccount(int accountId, boolean block);
    void addFunds(int accountId, double amount);
}
