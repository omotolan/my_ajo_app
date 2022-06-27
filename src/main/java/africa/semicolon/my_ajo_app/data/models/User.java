package africa.semicolon.my_ajo_app.data.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private double balance;
    private String accountNumber;
    private String pin;
    private String phoneNumber;

}
