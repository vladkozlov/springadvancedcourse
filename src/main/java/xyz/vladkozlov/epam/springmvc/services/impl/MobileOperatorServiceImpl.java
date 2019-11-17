package xyz.vladkozlov.epam.springmvc.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.vladkozlov.epam.springmvc.configurations.security.CustomUserPrincipal;
import xyz.vladkozlov.epam.springmvc.exceptions.NotEnoughMoneyException;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.models.UserAccount;
import xyz.vladkozlov.epam.springmvc.repositories.UserAccountRepository;
import xyz.vladkozlov.epam.springmvc.services.MobileOperatorService;

import java.util.Optional;

@Service
@Transactional
public class MobileOperatorServiceImpl implements MobileOperatorService {

    private UserAccountRepository userAccountRepository;

    public MobileOperatorServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override

    @Transactional
    public Optional<UserAccount> getCurrentUserAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = ((CustomUserPrincipal) authentication.getPrincipal()).getUser();
        return userAccountRepository.findByUser(principal);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    public Optional<UserAccount> changeOperator(String operator, String phoneNumber) throws NotEnoughMoneyException {
        final int SUM_TO_CHARGE = 150;

        UserAccount userAccount = this.getCurrentUserAccount().get();

        this.chargeMoney(userAccount, SUM_TO_CHARGE);
        userAccount.setCellOperator(operator);
        userAccount.setPhoneNumber(phoneNumber);
        return Optional.ofNullable(userAccountRepository.save(userAccount));
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    void chargeMoney(UserAccount userAccount, int sumToCharge) throws NotEnoughMoneyException {
        if (userAccount.getBalance() >= sumToCharge) {
            userAccount.setBalance(userAccount.getBalance() - sumToCharge);
            userAccountRepository.save(userAccount);
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}
