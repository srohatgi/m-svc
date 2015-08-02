package com.intocloudtech.controllers.web;

import com.intocloudtech.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * Created by sumeet on 7/31/15.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(Person person) {
        logger.debug("inside showForm");
        return "form";
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/results";
    }

}