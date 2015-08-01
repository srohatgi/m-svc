package com.intocloudtech.dao;

import com.intocloudtech.domain.Person;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by sumeet on 7/31/15.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);

}
