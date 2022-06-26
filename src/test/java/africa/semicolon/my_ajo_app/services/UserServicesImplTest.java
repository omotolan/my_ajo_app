package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.requestDto.LoginDto;
import africa.semicolon.my_ajo_app.data.requestDto.RegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserServices userServices;

    @Test
    public void testThatUseCanCreateAccount() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Akinsola Tolani");
        registerDto.setEmail("akinsolatolani@yahoo.com");
        registerDto.setPassword("semicolon");

        String response = userServices.createAccount(registerDto);
        assertEquals("Account Successfully created", response);
    }

    @Test
    public void testThatEmailIsAUniqueKey() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Love Alakija");
        registerDto.setEmail("lovealakija@yahoo.com");
        registerDto.setPassword("eledumare");

        String response = userServices.createAccount(registerDto);
        assertEquals("Account Successfully created", response);
    }

    @Test
    public void testThatRegisteredUserCanLogin() {
        LoginDto loginDto = new LoginDto();

        loginDto.setEmail("akinsolatolani@yahoo.com");
        loginDto.setPassword("semicolon");

        String response = userServices.loginToAccount(loginDto);
        assertEquals("Successfully logged in", response);
    }

    @Test
    public void testThatMoneyCanBeDeposited() {
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber("ghgh");
        depositDto.setAmount(40000.0);
        depositDto.setAccountNumber("4757061528");

        String response = userServices.depositToAccount(depositDto);
        assertEquals("40000.00 WAS SUCCESSFULLY DEPOSITED IN TO account number: 4757061528,", response);
    }
}