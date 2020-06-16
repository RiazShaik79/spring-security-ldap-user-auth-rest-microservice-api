package io.javabrains;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

import brave.sampler.Sampler;

@SpringBootApplication
@EntityScan
@EnableDiscoveryClient
public class SpringSecurityLdapApplication extends SpringBootServletInitializer {

	
	private static Logger log = LoggerFactory.getLogger(SpringSecurityLdapApplication.class);

    @Autowired private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLdapApplication.class, args);
	}
	
	/* @Bean
		public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		    DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		    System.setProperty("javax.net.ssl.keyStore", "src/main/resources/user-auth-cert.jks");
		    System.setProperty("javax.net.ssl.keyStorePassword", "India330$$");
		    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/user-auth-cert.jks");
		    System.setProperty("javax.net.ssl.trustStorePassword", "India330$$");
		    EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		    builder.withClientName("user-auth-cert");
		    builder.withSystemSSLConfiguration();
		    builder.withMaxTotalConnections(10);
		    builder.withMaxConnectionsPerHost(10);
		    args.setEurekaJerseyClient(builder.build());
		    return args;
		} */
	
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
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringSecurityLdapApplication.class); 
	} 
	
	   @Bean
	   public Sampler defaultSampler() {
		   return Sampler.ALWAYS_SAMPLE;
	   }


}
