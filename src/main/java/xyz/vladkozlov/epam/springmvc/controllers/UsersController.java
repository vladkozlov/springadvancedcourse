package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
            headers="accept=application/pdf")
    private ModelAndView getAllUsersAsPdf(){
        ModelAndView model = new ModelAndView();

        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);

        model.addObject("users", userList);
        model.setViewName("PdfReportViewGenerator");
        return model;
    }

    @GetMapping
    private ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();

        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);
        model.addObject("users", userList);
        model.setViewName("getAllUsers");
        return model;
    }
}
