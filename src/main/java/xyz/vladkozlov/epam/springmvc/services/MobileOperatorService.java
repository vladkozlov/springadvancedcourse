package xyz.vladkozlov.epam.springmvc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.vladkozlov.epam.springmvc.exceptions.NotEnoughMoneyException;
import xyz.vladkozlov.epam.springmvc.models.UserAccount;

import java.util.Optional;

@Service
public interface MobileOperatorService {

    Optional<UserAccount> getCurrentUserAccount();

    @Transactional
    Optional<UserAccount> changeOperator(String operator, String phoneNumber) throws NotEnoughMoneyException;

}
