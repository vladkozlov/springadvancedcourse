package xyz.vladkozlov.epam.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.vladkozlov.epam.springmvc.models.PhoneNumber;

public interface PhoneNumbersRepository extends CrudRepository<PhoneNumber, Long> {
}
