package africa.semicolon.my_ajo_app.services;

import africa.semicolon.my_ajo_app.data.models.User;
import africa.semicolon.my_ajo_app.data.repositories.UserRepository;
import africa.semicolon.my_ajo_app.data.requestDto.LoginDto;
import africa.semicolon.my_ajo_app.data.requestDto.RegisterDto;
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

        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        String accountNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        accountNumber = accountNumber.substring(1, 11);
        user.setAccountNumber(accountNumber);

        if (!Objects.equals(user.getEmail(), registerDto.getEmail())) {
            userRepository.save(user);

        } else {
            throw new IllegalArgumentException("Email already exists.");
        }

        return "Account Successfully created";
    }

    @Override
    public String loginToAccount(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null) {
            throw new IllegalArgumentException("Email does not exists");
        }

        if (!Objects.equals(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");

        }

        return "Successfully logged in";
    }

    @Override
    public String depositToAccount(DepositDto depositDto) {
        User user = userRepository.findByAccountNumber(depositDto.getAccountNumber());

        if (user == null) {
            throw new IllegalArgumentException("Account number does not exists");
        }
        if (depositDto.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount should be greater than zero");
        }
        user.setBalance(depositDto.getAmount());
        userRepository.save(user);

        return String.format("%.2f%S%s%s,", depositDto.getAmount(), " was successfully deposited in to",
                " account number: ", depositDto.getAccountNumber());
    }
}
