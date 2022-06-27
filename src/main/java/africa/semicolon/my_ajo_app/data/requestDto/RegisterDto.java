package africa.semicolon.my_ajo_app.data.requestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String pin;
    private String phoneNumber;
}
