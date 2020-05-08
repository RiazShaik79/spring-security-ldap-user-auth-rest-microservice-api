package io.javabrains;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
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
	private jwtUtil jwtTokenUtil;
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		AuthenticationResponse response = new AuthenticationResponse() ;
		
		Authentication authentication;
		try{
			 authentication = authenticationManager.authenticate(
		
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
 
		} 		catch(BadCredentialsException e) {
			System.out.println("error : " + e);
			response.setAuthStatus("Not Authorized");
			return response;
		//	throw new Exception("Incorrect username or password", e);
			
		}
		response.setAuthStatus("Authorized");
		return response;
	}
	
}
