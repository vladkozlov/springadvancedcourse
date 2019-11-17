package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "logout",	required = false) String logout){
        ModelAndView model = new ModelAndView();
        if (logout != null) {
            model.addObject("message", "Logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }
}
