package artur.goz.oop_lab1;
import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.Service.CreditCardServiceImpl;
import artur.goz.oop_lab1.models.CreditCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {
    @Mock
    private CreditCardDAO creditCardDAO;
    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    void addCreditCard_success() {
        CreditCard card = new CreditCard();
        creditCardService.addCreditCard(card);
        verify(creditCardDAO).addCreditCard(card);
    }

    @Test
    void getCreditCardsByUserId_success() {
        int userId = 1;
        List<CreditCard> cards = Arrays.asList(new CreditCard(), new CreditCard());

        when(creditCardDAO.getCardsByUserId(userId)).thenReturn(cards);

        List<CreditCard> result = creditCardService.getCreditCardsByUserId(userId);

        assertEquals(2, result.size());
        assertEquals(cards, result);
        verify(creditCardDAO).getCardsByUserId(userId);
    }
}

