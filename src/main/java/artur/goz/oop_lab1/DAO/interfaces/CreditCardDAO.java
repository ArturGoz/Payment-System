package artur.goz.oop_lab1.DAO.interfaces;

import artur.goz.oop_lab1.models.CreditCard;

import java.util.List;

public interface CreditCardDAO {
    void addCreditCard(CreditCard card);

    List<CreditCard> getCardsByUserId(int clientId);
}
