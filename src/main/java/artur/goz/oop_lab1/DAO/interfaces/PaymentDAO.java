package artur.goz.oop_lab1.DAO.interfaces;

import artur.goz.oop_lab1.models.Payment;

import java.util.List;

public interface PaymentDAO {
    void createPayment(Payment payment);

    List<Payment> getPaymentsByAccount(int accountId);
}
