package com.intocloudtech.controllers.api;

import com.intocloudtech.domain.Person;
import com.intocloudtech.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sumeet on 7/31/15.
 */
@RestController
@RequestMapping("/api")
public class PeopleController {
    @Autowired
    private PersonService personService;

    @RequestMapping("people")
    public List<Person> findPerson(@RequestParam String fname) {
        return personService.findPersonByFirstName(fname);
    }

}
