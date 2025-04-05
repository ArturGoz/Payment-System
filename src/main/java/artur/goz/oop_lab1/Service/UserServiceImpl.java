package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.DAO.interfaces.PaymentDAO;
import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.Service.interfaces.UserService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.CreditCard;
import artur.goz.oop_lab1.models.Payment;
import artur.goz.oop_lab1.models.User;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private final CreditCardDAO creditCardDAO;
    private final PaymentDAO paymentDAO;

/*    @PostConstruct
    public void init() {
        createRandomUserWithCardsAndAccounts();
    }*/

    @Override
    public void addUser(User user) {
        userDAO.createUser(user);
    }

    @Override
    public void createRandomUserWithCardsAndAccounts() {
        // 1. Генерація та збереження користувача
        User user = generateRandomUser();
        user = userDAO.createUser(user);

        // 2. Генерація та збереження акаунта
        Account account = generateRandomAccount(user.getId());
        account = accountDAO.createAccount(account);

        // 3. Генерація та збереження кредитної картки
        CreditCard creditCard = generateRandomCreditCard(user.getId(), account.getId());
        creditCardDAO.addCreditCard(creditCard);

        // 4. Генерація та збереження платежу
        Payment payment = generateRandomPayment(account.getId());
        paymentDAO.createPayment(payment);
    }

    // Допоміжні методи для генерації випадкових об'єктів
    private User generateRandomUser() {
        Random random = new Random();
        User user = new User();
        user.setName("User" + random.nextInt(100));
        user.setLogin("login" + random.nextInt(100));
        user.setPassword("pass" + random.nextInt(100)); // У реальності потрібне хешування
        user.setRole("user");
      //  user.setRole(random.nextBoolean() ? "user" : "admin");
        return user;
    }

    private Account generateRandomAccount(int userId) {
        Random random = new Random();
        Account account = new Account();
        account.setBalance(random.nextDouble() * 1000); // Баланс від 0 до 1000
        account.setBlocked(false);
        return account;
    }

    private CreditCard generateRandomCreditCard(int userId, int accountId) {
        Random random = new Random();
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("CARD" + random.nextInt(10000)); // Випадковий номер
        creditCard.setUserId(userId);
        creditCard.setAccountId(accountId);
        creditCard.setExpirationDate(LocalDate.now().plusYears(random.nextInt(5) + 1)); // Термін дії 1-5 років
        return creditCard;
    }

    private Payment generateRandomPayment(int accountId) {
        Random random = new Random();
        Payment payment = new Payment();
        payment.setAccountId(accountId);
        payment.setAmount(random.nextDouble() * 500); // Сума від 0 до 500
        payment.setTimestamp(LocalDateTime.now());
        return payment;
    }
}
