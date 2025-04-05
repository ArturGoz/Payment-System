package artur.goz.oop_lab1;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.Service.AccountServiceImpl;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.CreditCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountDAO accountDAO;
    @Mock
    private CreditCardDAO creditCardDAO;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getAccountsByUserId_success() {
        int userId = 1;
        CreditCard card1 = new CreditCard();
        card1.setAccountId(1);
        CreditCard card2 = new CreditCard();
        card2.setAccountId(2);
        List<CreditCard> cards = Arrays.asList(card1, card2);
        Account account1 = new Account();
        Account account2 = new Account();

        when(creditCardDAO.getCardsByUserId(userId)).thenReturn(cards);
        when(accountDAO.getAccount(1)).thenReturn(account1);
        when(accountDAO.getAccount(2)).thenReturn(account2);

        List<Account> result = accountService.getAccountsByUserId(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(account1));
        assertTrue(result.contains(account2));
        verify(creditCardDAO).getCardsByUserId(userId);
        verify(accountDAO, times(2)).getAccount(anyInt());
    }

    @Test
    void blockAccount_success() {
        int accountId = 1;
        accountService.blockAccount(accountId);
        verify(accountDAO).blockAccount(accountId, true);
    }

    @Test
    void unblockAccount_success() {
        int accountId = 1;
        accountService.unblockAccount(accountId);
        verify(accountDAO).blockAccount(accountId, false);
    }
}