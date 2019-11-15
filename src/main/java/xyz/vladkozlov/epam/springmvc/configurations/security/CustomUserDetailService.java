package xyz.vladkozlov.epam.springmvc.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isPresent()) {
            return new CustomUserPrincipal(user.get());
        } else throw new UsernameNotFoundException(username);
    }


    public User registerNewUser(User user) {
        if (userExist(user)) {
            throw new RuntimeException("User Exist");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getUsername()));
        newUser.setFullName(user.getFullName());
        newUser.setPhoneNumbers(user.getPhoneNumbers());
        newUser.setPhoneNumbers(user.getPhoneNumbers());
        newUser.setRoles(user.getRoles());
        return usersRepository.save(newUser);
    }

    private boolean userExist(User user) {
        return usersRepository.findByUsername(user.getUsername()).isPresent();
    }
}
