package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.vladkozlov.epam.springmvc.exceptions.UserAlreadyExistException;
import xyz.vladkozlov.epam.springmvc.exceptions.UserNotFoundException;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class RestUsersManipulationController {

    private PasswordEncoder passwordEncoder;

    private UsersRepository usersRepository;

    public RestUsersManipulationController(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> u = usersRepository.findById(userId);
        return usersRepository
                .findById(userId)
                .orElseThrow(()->new UserNotFoundException(userId));
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        usersRepository
                .findAll()
                .forEach(userList::add);
        return  userList;
    }


    @GetMapping("/sorted/{order}")
    public List<User> getUsersByOrder(@PathVariable String order) {
        String ordLow = order.toLowerCase();
        if (ordLow.equals("asc")) {
            return usersRepository
                    .findAllByOrderByFullNameAsc()
                    .orElse(Collections.emptyList());
        } else if (ordLow.equals("desc")) {
            return usersRepository
                    .findAllByOrderByFullNameDesc()
                    .orElse(Collections.emptyList());
        }
        throw new IllegalArgumentException("Order could be 'asc' or 'desc'");
    }

    @GetMapping("/{page}/{size}")
    public List<User> getUsersPageLimited(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return usersRepository.findAll(pageable)
                .orElse(Collections.emptyList());
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
        if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(user.getUsername());
        }

        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPhoneNumbers(user.getPhoneNumbers());
        newUser.setRoles(user.getRoles());
        return usersRepository.save(newUser);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        usersRepository.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public User editUser(@PathVariable Long userId, @RequestBody User newUser) throws UserNotFoundException {
        return usersRepository.findById(userId).
                map(user -> {
                    if (newUser.getFullName() != null) {
                        user.setFullName(newUser.getFullName());
                    }

                    if (newUser.getUsername() != null) {
                        user.setUsername(newUser.getUsername());
                    }

                    if (newUser.getPassword() != null) {
                        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    }

                    if (newUser.getRoles() != null) {
                        user.setRoles(newUser.getRoles());
                    }
                    return usersRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
