package io.javabrains;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private jwtUtil jwtUtil;
	
	@Autowired
	private PersonRepository personRepository;
	
	private Person person;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		final String authorizationHeader = request.getHeader("Authorization");
		String username =null;
		String jwt = null;
		
	
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
			//System.out.println(authorizationHeader.substring(7));
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (jwtUtil.ValidateToken(jwt)) {
				System.out.println("Token validated");
				
				person = personRepository.findOne(jwtUtil.extractUsername(jwt));
				System.out.println("Token validated");	
			
				try {
			//	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jwtUtil.extractUsername(jwt),usernamePasswordAuthenticationToken);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(person.getUid(),person.getUserPassword());
				System.out.println(person.getFullName() + " " + person.getLastName() + " " + person.getUserPassword());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				filterChain.doFilter(request, response);
			}
			
			catch (Exception e) {
				System.out.println("error" + e);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	}

}	
