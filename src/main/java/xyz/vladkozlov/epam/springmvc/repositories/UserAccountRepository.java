package xyz.vladkozlov.epam.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.models.UserAccount;

import java.util.Optional;

@Transactional(propagation= Propagation.REQUIRED)
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    Optional<UserAccount> findByUser(User user);
}
