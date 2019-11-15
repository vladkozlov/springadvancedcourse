package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/userspdf", method = RequestMethod.GET,
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
}
