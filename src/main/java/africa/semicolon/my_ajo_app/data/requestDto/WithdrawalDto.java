package africa.semicolon.my_ajo_app.data.requestDto;

import lombok.Data;
import lombok.Setter;

@Data
public class WithdrawalDto {
    @Setter
    private double amount;

    private String accountNumber;
}
