package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import xyz.vladkozlov.epam.springmvc.models.RandomCat;

@RestController
public class RestClientDataParserController {

    private final String RANDOM_FOX_API_URL = "https://aws.random.cat/meow";

    @GetMapping("/rest/images/cat")
    public RandomCat getRandomFoxImage() {
        RestTemplate restTemplate = new RestTemplate();
        RandomCat response = restTemplate.getForObject(RANDOM_FOX_API_URL, RandomCat.class);
        return response;
    }

    @GetMapping("/rest/info")
    public ModelAndView getDocs(){
        return new ModelAndView("getRestApiDocs");
    }
}
