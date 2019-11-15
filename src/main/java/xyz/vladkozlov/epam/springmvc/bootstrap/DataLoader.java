package xyz.vladkozlov.epam.springmvc.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.vladkozlov.epam.springmvc.models.PhoneNumber;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("LOADING USER DATA");
        loadData();
    }

    private void loadData() {
        //User vlad
        User vlad = new User();
        vlad.setUsername("vlad");
        vlad.setPassword(passwordEncoder.encode("vlad"));
        vlad.setFullName("Vladislav Kozlov");
        vlad.addRole("REGISTERED_USER");

        Set<PhoneNumber> vladsPhoneNumbers = new HashSet<>(2);
        vladsPhoneNumbers.add(new PhoneNumber("Beats", "+000000000"));
        vladsPhoneNumbers.add(new PhoneNumber("Drones", "+111111111"));

        vlad.setPhoneNumbers(vladsPhoneNumbers);
        usersRepository.save(vlad);

        //User admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFullName("Booking Manager Guy");
        admin.addRoles("REGISTERED_USER", "BOOKING_MANAGER");

        Set<PhoneNumber> bookingGuyNumbers = new HashSet<>(1);
        bookingGuyNumbers.add(new PhoneNumber("Booking Agency", "+12312312313"));

        admin.setPhoneNumbers(bookingGuyNumbers);
        usersRepository.save(admin);
    }
}
