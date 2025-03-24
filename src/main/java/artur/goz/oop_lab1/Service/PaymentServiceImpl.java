package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.PaymentDAO;
import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.Payment;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final AccountDAO accountDAO;

    @Override
    public void processPayment(int accountId, double amount) {
        Account account = accountDAO.getAccount(accountId);
        validatePayment(account, amount);

        accountDAO.updateBalance(accountId, -amount);
        createPaymentRecord(accountId, amount);
    }

    @Override
    public List<Payment> getPaymentsHistory(int accountId) {
        return List.of();
    }

    private void validatePayment(Account account, double amount) throws RuntimeException {
        if (account == null) throw new RuntimeException("Account not found");
        if (account.isBlocked()) throw new RuntimeException("Account is blocked");
        if (account.getBalance() < amount) throw new RuntimeException("Insufficient funds");
    }

    private void createPaymentRecord(int accountId, double amount) {
        Payment payment = new Payment();
        payment.setAccountId(accountId);
        payment.setAmount(amount);
        payment.setTimestamp(LocalDateTime.now());
        paymentDAO.createPayment(payment);
    }
}
