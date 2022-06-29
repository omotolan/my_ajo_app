package africa.semicolon.my_ajo_app.data.requestDto;

import lombok.Data;
import lombok.Setter;

@Data
public class RechargeAirtimeDto {
    @Setter
    private String phoneNumber;
    private String accountNumber;
    private double amount;
}
