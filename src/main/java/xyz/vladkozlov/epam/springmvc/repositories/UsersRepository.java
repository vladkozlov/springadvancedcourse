package xyz.vladkozlov.epam.springmvc.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import xyz.vladkozlov.epam.springmvc.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<List<User>> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

    Optional<List<User>> findAllByOrderByFullNameDesc();

    Optional<List<User>> findAllByOrderByFullNameAsc();
}
