package artur.goz.oop_lab1.Service.interfaces;

import artur.goz.oop_lab1.models.CreditCard;

import java.util.List;

public interface CreditCardService {
    void addCreditCard(CreditCard card);

    List<CreditCard> getCreditCardsByUserId(int userId);
}
