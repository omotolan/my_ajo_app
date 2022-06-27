package africa.semicolon.my_ajo_app.data.requestDto;

import lombok.Data;

@Data
public class DepositDto {
    private String accountNumber;
    private double amount;
}
