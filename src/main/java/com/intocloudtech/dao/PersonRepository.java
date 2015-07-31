package com.intocloudtech.dao;

import com.intocloudtech.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by sumeet on 7/31/15.
 */
@Repository
public class PersonRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Person findByFirstName(String firstName) {

        MongoOperations mongoOps = mongoTemplate;

        mongoOps.insert(new Person("Joe", "Sim", 34));

        return mongoOps.findOne(new Query(where("firstName").is("Joe")), Person.class);
    }
}
