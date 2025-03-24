package artur.goz.oop_lab1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockRequest {
    private int id;
    private int accountId;
    private LocalDateTime requestDate;
    private boolean resolved;
}
