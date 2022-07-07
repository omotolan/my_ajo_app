package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.models.User;
import africa.semicolon.my_ajo_app.data.repositories.UserRepository;
import africa.semicolon.my_ajo_app.data.requestDto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createAccount(RegisterDto registerDto) {
        User user = new User();


       // user = Mapper.map(registerDto);
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setPin(registerDto.getPin());
        user.setPhoneNumber(registerDto.getPhoneNumber());

        String accountNumber = generateAccountNumber();
        user.setAccountNumber(accountNumber);

        if (!Objects.equals(user.getEmail(), registerDto.getEmail())) {
            userRepository.save(user);

        } else {
            throwException("Email already exists.");
        }

        return "Account Successfully created";
    }


    @Override
    public String loginToAccount(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null) {
            throwException("Email does not exists");
        }

        if (!Objects.equals(loginDto.getPassword(), user.getPassword())) {
            throwException("Incorrect password");
        }

        return "Successfully logged in";
    }


    @Override
    public String depositToAccount(DepositDto depositDto) {
        User user = userRepository.findByAccountNumber(depositDto.getAccountNumber());

        if (user == null) {
            throwException("Account number does not exists");
        }
        if (depositDto.getAmount() <= 0) {
            throwException("Amount should be greater than zero");

        }
        user.setBalance(depositDto.getAmount());
        userRepository.save(user);

        return String.format("%.2f%S%s%s,", depositDto.getAmount(), " was successfully deposited in to",
                " account number: ", depositDto.getAccountNumber());
    }


    @Override
    public String withdraw(WithdrawalDto withdrawalDto) {
        User user = userRepository.findByAccountNumber(withdrawalDto.getAccountNumber());
        double newBalance;
        if (withdrawalDto.getAmount() <= 0.0) {
            throwException("Amount should be greater than 0");
        }
        if (withdrawalDto.getAmount() > user.getBalance()) {
            throwException("Insufficient Balance");
        }
        newBalance = user.getBalance() - withdrawalDto.getAmount();
        user.setBalance(newBalance);
        userRepository.save(user);

        return String.format("%.2f%s%.2f", withdrawalDto.getAmount(), " was successfully debited from you account." +
                " your new balance is: ", user.getBalance());
    }


    @Override
    public String transfer(TransferDto transferDto, DepositDto depositDto) {
        User user = userRepository.findByAccountNumber(transferDto.getAccountNumber());

        if (user == null) {
            throwException("Invalid account number ");
        }
        if (transferDto.getAmount() > user.getBalance()) {
            throwException("Insufficient balance");
        }
        if (transferDto.getAmount() < 0.0) {
            throwException("Amount should be greater than 0");
        }
        double newBalance = user.getBalance() - transferDto.getAmount();
        user.setBalance(newBalance);
        if (!Objects.equals(user.getPin(), transferDto.getPin())) {
            throwException("pin not correct");
        }

        User user1 = userRepository.findByAccountNumber(depositDto.getAccountNumber());
        if (user1 == null) {
            throwException("Invalid account number ");
        }

        user1.setBalance(user1.getBalance() + transferDto.getAmount());

        userRepository.save(user);
        userRepository.save(user1);
        return String.format("%.2f%s%s", transferDto.getAmount(), " was successfully transferred to ",
                depositDto.getAccountNumber());
    }


    @Override
    public String balance(BalanceDto balanceDto) {
        User user = userRepository.findByAccountNumber(balanceDto.getAccountNumber());

        return "Your account balance is: " + user.getBalance();
    }


    @Override
    public String rechargeAirtime(RechargeAirtimeDto rechargeAirtimeDto) {
        User user = userRepository.findByAccountNumber(rechargeAirtimeDto.getAccountNumber());

        if (rechargeAirtimeDto.getAmount() < 0) {
            throwException("Amount has to be greater than 0");
        }
        if (rechargeAirtimeDto.getAmount() > 10000.0) {
            throwException("Amount exceeds recharge limit");
        }
        if (rechargeAirtimeDto.getAmount() > user.getBalance()) {
            throwException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - rechargeAirtimeDto.getAmount());
        userRepository.save(user);

        return rechargeAirtimeDto.getPhoneNumber() + " successfully recharged with: " + rechargeAirtimeDto.getAmount();
    }

    private String generateAccountNumber() {

        String accountNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        accountNumber = accountNumber.substring(1, 11);

        return accountNumber;

    }

    private void throwException(String message) {

        throw new IllegalArgumentException(message);
    }
}
