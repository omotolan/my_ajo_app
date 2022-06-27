package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.requestDto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {

    String createAccount(RegisterDto registerDto);

    String loginToAccount(LoginDto loginDto);

    String depositToAccount(DepositDto depositDto);

    String withdraw(WithdrawalDto withdrawalDto);

    String transfer(TransferDto transferDto, DepositDto depositDto);

    String balance(BalanceDto balanceDto);

    String rechargeAirTime(RechargeAirTimeDto rechargeAirTimeDto);
}
