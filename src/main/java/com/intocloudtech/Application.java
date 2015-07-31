package com.intocloudtech;

import com.intocloudtech.domain.Person;
import com.intocloudtech.services.PersonService;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private PersonService personService;

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}

    @RequestMapping("/persons")
    public List<Person> findPerson() {
        return personService.findPersonByFirstName("hello");
    }

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo("192.168.33.10"), "database");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
