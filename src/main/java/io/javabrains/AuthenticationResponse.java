package io.javabrains;

public class AuthenticationResponse {
	
	public AuthenticationResponse(String jwt) {
		
		Jwt = jwt;
	}

	private final String Jwt;
	
	public String getJwt( ) {
		return Jwt;
	}
	
	

}
