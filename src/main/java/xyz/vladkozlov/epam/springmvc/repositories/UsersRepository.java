package xyz.vladkozlov.epam.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.vladkozlov.epam.springmvc.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
