package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.requestDto.*;
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
        registerDto.setPin("1234");
        registerDto.setPhoneNumber("08182769505");


        String response = userServices.createAccount(registerDto);
//        assertEquals("Account Successfully created", response);
        assertThrows(IllegalArgumentException.class, () -> userServices.createAccount(registerDto));
    }

    @Test
    public void testThatEmailIsAUniqueKey() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Love Alakija");
        registerDto.setEmail("lovealakija@yahoo.com");
        registerDto.setPassword("eledumare");
        registerDto.setPin("4567");
        registerDto.setPin("07035428767");

         String response = userServices.createAccount(registerDto);
//         assertEquals("Account Successfully created", response);
        assertThrows(IllegalArgumentException.class, () -> userServices.createAccount(registerDto));
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

        depositDto.setAccountNumber("4537148721");
        depositDto.setAmount(40000.0);

        String response = userServices.depositToAccount(depositDto);
        assertEquals("40000.00 WAS SUCCESSFULLY DEPOSITED IN TO account number: 4537148721,", response);
    }

    @Test
    public void testThatUserCanWithdraw() {
        WithdrawalDto withdrawalDto = new WithdrawalDto();

        withdrawalDto.setAccountNumber("4537148721");
        withdrawalDto.setAmount(2000.0);


        String response = userServices.withdraw(withdrawalDto);
        assertEquals("2000.00 was successfully debited from you account. your new balance is: 38000.00", response);
    }

    @Test
    public void testThatAccountCanTransfer() {
        TransferDto transferDto = new TransferDto();
        DepositDto depositDto = new DepositDto();

        transferDto.setAccountNumber("4537148721");
        transferDto.setAmount(1500.0);
        transferDto.setPin("1234");
        depositDto.setAccountNumber("4995478630");

        String response = userServices.transfer(transferDto, depositDto);
        assertEquals("1500.00 was successfully transferred to 4995478630", response);


    }
    @Test
    public void testThanBalanceCanBeGotten(){
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setAccountNumber("4537148721");

        String response = userServices.balance(balanceDto);

        assertEquals("Your account balance is: 36500.0", response);
    }
    @Test
    public void testThatAirTimeCanBeRechargedFromAccount(){
        RechargeAirtimeDto rechargeAirtimeDto = new RechargeAirtimeDto();

        rechargeAirtimeDto.setAccountNumber("4995478630");
        rechargeAirtimeDto.setPhoneNumber("07035428767");
        rechargeAirtimeDto.setAmount(500.0);

        String response = userServices.rechargeAirtime(rechargeAirtimeDto);
        assertEquals("07035428767 successfully recharged with: 500.0", response);
    }
}