package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.Service.interfaces.CreditCardService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final CreditCardDAO creditCardDAO;

    @Override
    public List<Account> getAccountsByUserId(int userId)  {
        List<CreditCard> creditCardList = creditCardDAO.getCardsByUserId(userId);
        List<Account> accountList = new ArrayList<>();
        for (CreditCard creditCard : creditCardList) {
            Account account = accountDAO.getAccount(creditCard.getAccountId());
            accountList.add(account);
        }
        return accountList;
    }

    @Override
    public void blockAccount(int accountId) {
        accountDAO.blockAccount(accountId, true);
    }

    @Override
    public void unblockAccount(int accountId) {
        accountDAO.blockAccount(accountId, false);
    }

}
