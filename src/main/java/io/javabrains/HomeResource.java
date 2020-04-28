package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.innovativeintelli.ldapauthenticationjwttoken.security.JwtTokenProvider;

@RestController
public class HomeResource {
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private jwtUtil jwtTokenUtil;
	
	//private User user;
	
	@RequestMapping("/")
	public String index() {
		return "Home Page";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!..";
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		Authentication authentication;
		try{
			 authentication = authenticationManager.authenticate(
		
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
 
		} 		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		//final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String Jwt = jwtTokenUtil.generateToken(authentication);
		
		return ResponseEntity.ok(new AuthenticationResponse(Jwt));
	}

}
