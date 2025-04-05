package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.PaymentDAO;
import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.Payment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final AccountDAO accountDAO;
    private final AccountService accountService;

    @Override
    public void processPayment(int accountIdToPay, int accountIdToGet, double amount) {
        Account accountToPay = accountDAO.getAccount(accountIdToPay);
        Account accountToGet = accountDAO.getAccount(accountIdToGet);

        validatePayment(accountToPay, amount);
        validateAccount(accountToGet);

        accountDAO.updateBalance(accountIdToPay, -amount);
        accountDAO.updateBalance(accountIdToGet, amount);

        createPaymentRecord(accountIdToPay, -amount);
        createPaymentRecord(accountIdToGet, amount);
    }

    @Override
    public void addFunds(int accountId, double amount) {
        Account account = accountDAO.getAccount(accountId);
        validateAccount(account);

        accountDAO.updateBalance(accountId, amount);
        createPaymentRecord(accountId, amount);
    }

    @Override
    public List<Payment> getPaymentsByUserId(int userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        List<Payment> payments = new ArrayList<>();
        for (Account account : accounts) {
            List<Payment> paymentsForAccount = paymentDAO.getPaymentsByAccount(account.getId());
            payments.addAll(paymentsForAccount);
        }
        return payments;
    }


    private void validatePayment(Account account, double amount) throws RuntimeException {
        validateAccount(account);
        if (account.getBalance() < amount) throw new RuntimeException("Insufficient funds");
    }

    private void validateAccount(Account account) throws RuntimeException {
        if (account == null) throw new RuntimeException("Account not found");
        if (account.isBlocked()) throw new RuntimeException("Account is blocked");
    }

    private void createPaymentRecord(int accountId, double amount) {
        Payment payment = new Payment();
        payment.setAccountId(accountId);
        payment.setAmount(amount);
        payment.setTimestamp(LocalDateTime.now());
        paymentDAO.createPayment(payment);
    }
}
