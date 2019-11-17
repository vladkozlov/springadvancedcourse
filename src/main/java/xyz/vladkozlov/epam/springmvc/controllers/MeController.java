package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.vladkozlov.epam.springmvc.configurations.security.CustomUserPrincipal;
import xyz.vladkozlov.epam.springmvc.exceptions.NotEnoughMoneyException;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.models.UserAccount;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;
import xyz.vladkozlov.epam.springmvc.services.MobileOperatorService;

import java.util.Optional;

@Controller
@RequestMapping("/me")
public class MeController {

    private UsersRepository usersRepository;

    private MobileOperatorService mobileOperatorService;

    public MeController(UsersRepository usersRepository, MobileOperatorService mobileOperatorService) {
        this.usersRepository = usersRepository;
        this.mobileOperatorService = mobileOperatorService;
    }

    @GetMapping
    private ModelAndView getUserData(Authentication auth){
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

    @RequestMapping(value = "/account")
    private ModelAndView myAcc(Authentication authentication) {
        ModelAndView model = new ModelAndView();
        model.setViewName("getMyAccount");
        UserAccount account = mobileOperatorService.getCurrentUserAccount().get();

        model.addObject("user", ((CustomUserPrincipal) authentication.getPrincipal()).getUser());
        model.addObject("account", account);
        return model;
    }

    @PostMapping(value = "/changeMobileOperator")
    public ModelAndView changeMobileOperator(Authentication auth,
                                             @RequestParam(value = "operator") String operatorName,
                                             @RequestParam(value = "number") String phoneNumber, ModelMap model)
            throws NotEnoughMoneyException {

        mobileOperatorService.changeOperator(operatorName, phoneNumber);
        model.addAttribute("redirect", "redirectFromChangeMobileOperator");
        return new ModelAndView("redirect:/me/account", model);
    }

}
