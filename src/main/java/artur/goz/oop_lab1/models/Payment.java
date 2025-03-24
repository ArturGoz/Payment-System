package artur.goz.oop_lab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int id;
    private int accountId;
    private double amount;
    private LocalDateTime timestamp;
}
