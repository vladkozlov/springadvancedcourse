package xyz.vladkozlov.epam.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.vladkozlov.epam.springmvc.models.User;

public interface UsersRepository extends CrudRepository<User, Long> {
//    @Query("Select u.fullName, u.phoneNumbers from User u  left join PhoneNumber b on b.userId=a.id")
//    public Optional<User> findAllWithPhoneNumbers();
}
