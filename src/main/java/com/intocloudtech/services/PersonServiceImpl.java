package com.intocloudtech.services;

import com.intocloudtech.dao.PersonRepository;
import com.intocloudtech.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by sumeet on 7/31/15.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findPersonByFirstName(String fname) {
        logger.debug("fname = {}", fname);
        List<Person> personList = new ArrayList<>();
        personList.add(personRepository.findByFirstName(fname));
        return personList;
    }
}
