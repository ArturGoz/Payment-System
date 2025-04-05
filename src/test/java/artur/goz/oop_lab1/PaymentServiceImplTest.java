package artur.goz.oop_lab1;


import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.PaymentDAO;
import artur.goz.oop_lab1.Service.PaymentServiceImpl;
import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentDAO paymentDAO;
    @Mock
    private AccountDAO accountDAO;
    @Mock
    private AccountService accountService;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void processPayment_success() {
        int accountIdToPay = 1;
        int accountIdToGet = 2;
        double amount = 100.0;
        Account accountToPay = new Account();
        accountToPay.setBalance(200.0);
        Account accountToGet = new Account();

        when(accountDAO.getAccount(accountIdToPay)).thenReturn(accountToPay);
        when(accountDAO.getAccount(accountIdToGet)).thenReturn(accountToGet);

        paymentService.processPayment(accountIdToPay, accountIdToGet, amount);

        verify(accountDAO).updateBalance(accountIdToPay, -amount);
        verify(accountDAO).updateBalance(accountIdToGet, amount);
        verify(paymentDAO, times(2)).createPayment(any(Payment.class));
    }

    @Test
    void processPayment_insufficientFunds_throwsException() {
        int accountIdToPay = 1;
        int accountIdToGet = 2;
        double amount = 100.0;
        Account accountToPay = new Account();
        accountToPay.setBalance(50.0);
        Account accountToGet = new Account();

        when(accountDAO.getAccount(accountIdToPay)).thenReturn(accountToPay);
        when(accountDAO.getAccount(accountIdToGet)).thenReturn(accountToGet);

        assertThrows(RuntimeException.class, () ->
                paymentService.processPayment(accountIdToPay, accountIdToGet, amount));
    }

    @Test
    void addFunds_success() {
        int accountId = 1;
        double amount = 100.0;
        Account account = new Account();

        when(accountDAO.getAccount(accountId)).thenReturn(account);

        paymentService.addFunds(accountId, amount);

        verify(accountDAO).updateBalance(accountId, amount);
        verify(paymentDAO).createPayment(any(Payment.class));
    }

    @Test
    void getPaymentsByUserId_success() {
        int userId = 1;
        Account account = new Account();
        account.setId(1);
        List<Account> accounts = List.of(account);
        Payment payment = new Payment();
        List<Payment> payments = List.of(payment);

        when(accountService.getAccountsByUserId(userId)).thenReturn(accounts);
        when(paymentDAO.getPaymentsByAccount(1)).thenReturn(payments);

        List<Payment> result = paymentService.getPaymentsByUserId(userId);

        assertEquals(1, result.size());
        assertEquals(payments, result);
        verify(accountService).getAccountsByUserId(userId);
        verify(paymentDAO).getPaymentsByAccount(1);
    }
}
