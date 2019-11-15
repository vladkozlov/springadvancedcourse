package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", e.getMessage());
        model.setViewName("error");
        return model;
    }
}
