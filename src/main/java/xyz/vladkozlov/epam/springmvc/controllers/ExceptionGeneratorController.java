package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class ExceptionGeneratorController {
    @GetMapping("/errorplease")
    public String giveMeErrorPlease() throws Exception {
        Random random = new Random();
        throw new Exception("Custom error generated. Error code: " + random.nextInt());
    }
}
