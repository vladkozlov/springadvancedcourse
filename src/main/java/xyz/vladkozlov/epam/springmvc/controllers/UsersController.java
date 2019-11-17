package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.vladkozlov.epam.springmvc.configurations.security.CustomUserPrincipal;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/users/pdf", method = RequestMethod.GET,
            headers="accept=application/pdf")
    private ModelAndView getAllUsersAsPdf(){
        ModelAndView model = new ModelAndView();

        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);

        model.addObject("users", userList);
        model.setViewName("PdfReportViewGenerator");
        return model;
    }

    @GetMapping(value = "/users")
    private ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();

        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);
        model.addObject("users", userList);
        model.setViewName("getAllUsers");
        return model;
    }

    @GetMapping(value = "/me")
    private ModelAndView getUserData(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserPrincipal) auth.getPrincipal()).getUsername();
        ModelAndView model = new ModelAndView();
        model.setViewName("getMyProfile");
        Optional<User> user = usersRepository.findByUsername(username);
        model.addObject("user", user.orElse(new User()));
        if (auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("BOOKING_MANAGER"))) {
            model.addObject("isBookingManager", true);
        }
        return model;
    }
}
