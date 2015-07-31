package com.intocloudtech.services;

import com.intocloudtech.dao.PersonRepository;
import com.intocloudtech.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sumeet on 7/31/15.
 */
public interface PersonService {

    List<Person> findPersonByFirstName(String fname);

}
