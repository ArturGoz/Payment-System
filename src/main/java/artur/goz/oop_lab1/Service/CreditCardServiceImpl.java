package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.Service.interfaces.CreditCardService;
import artur.goz.oop_lab1.models.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardDAO creditCardDAO;

    @Override
    public void addCreditCard(CreditCard card) {
        creditCardDAO.addCreditCard(card);
    }

    @Override
    public List<CreditCard> getCreditCardsByUserId(int userId) {
        return creditCardDAO.getCardsByUserId(userId);
    }
}
