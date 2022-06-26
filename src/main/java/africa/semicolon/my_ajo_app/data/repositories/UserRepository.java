package africa.semicolon.my_ajo_app.data.repositories;

import africa.semicolon.my_ajo_app.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByAccountNumber(String accountNumber);
}
