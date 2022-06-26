package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.requestDto.LoginDto;
import africa.semicolon.my_ajo_app.data.requestDto.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {

    String createAccount(RegisterDto registerDto);

    String loginToAccount(LoginDto loginDto);

    String depositToAccount(DepositDto depositDto);
}
