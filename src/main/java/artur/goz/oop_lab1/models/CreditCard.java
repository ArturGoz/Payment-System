package artur.goz.oop_lab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String cardNumber;
    private int userId;
    private int accountId;
    private LocalDate expirationDate;
}
