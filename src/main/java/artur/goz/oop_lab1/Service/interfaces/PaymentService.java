package artur.goz.oop_lab1.Service.interfaces;

import artur.goz.oop_lab1.models.Payment;

import java.util.List;

public interface PaymentService {
    void processPayment(int accountIdToPay, int accountIdToGet, double amount);
    void addFunds(int accountId, double amount);
    List<Payment> getPaymentsByUserId(int userId);
}
