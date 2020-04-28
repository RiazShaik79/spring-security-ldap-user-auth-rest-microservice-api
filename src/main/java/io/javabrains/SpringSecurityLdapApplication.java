package io.javabrains;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class SpringSecurityLdapApplication {

	
	private static Logger log = LoggerFactory.getLogger(SpringSecurityLdapApplication.class);

    @Autowired private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLdapApplication.class, args);
	}
	
	@PostConstruct
    public void setup(){
        log.info("Spring LDAP CRUD Operations Binding and Unbinding Example");

        log.info("- - - - - - Managing persons");

        List<Person> persons = personRepository.findAll();
        log.info("persons: " + persons);

        Person bob = personRepository.findOne("bob");
        bob.setLastName("custom last name");
        personRepository.updateLastName(bob); 
        
        persons = personRepository.findAll();
        log.info("persons: " + persons);
  
       //Person person = new Person("uid", "new person1", "person1");
       //personRepository.create(person);
     
        persons = personRepository.findAll();
        log.info("persons: " + persons);

      //  System.exit(-1);
    }


}
