package artur.goz.oop_lab1.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentDAOImpl implements PaymentDAO {
    private final PaymentDAO paymentDAO;
    private final AccountDAO accountDAO;
}
