package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;

    @Override
    public List<Account> getUserAccounts(int clientId)  {
        return accountDAO.getUserAccounts(clientId);
    }

    @Override
    public void blockAccount(int accountId) {
        accountDAO.blockAccount(accountId, true);
    }

    @Override
    public void unblockAccount(int accountId) {
        accountDAO.blockAccount(accountId, false);
    }

    @Override
    public void addFunds(int accountId, double amount) {
        accountDAO.addFunds(accountId, amount);
    }
}
