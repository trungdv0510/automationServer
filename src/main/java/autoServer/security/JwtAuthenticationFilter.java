package autoServer.security;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import autoServer.DTO.UserDTO;
import autoServer.DTO.UserPrincipal;
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static List<Cookie> listCookies = new LinkedList<Cookie>();
	 private final AuthenticationManager authenticationManager;

	    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }

	    /* Trigger when we issue POST request to /login
	    We also need to pass in {"username":"dan", "password":"dan123"} in the request body
	     */
	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	    	 Authentication auth = null;
	    	try {
	    		 // Grab credentials and map them to login viewmodel
		        UserDTO credentials = null;
		        try {
		            credentials = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        // Create login token
		        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		                credentials.getUsername(),
		                credentials.getPassword(),
		                new ArrayList<>());

		        // Authenticate user
		        auth = authenticationManager.authenticate(authenticationToken);
			} catch (NullPointerException  e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
				try {
					 ObjectMapper mapper = new ObjectMapper();
		        	 response.setStatus(401);
		 				mapper.writeValue(response.getOutputStream(), "Null value");
				} catch (Exception e2) {
					// TODO: handle exception
					logger.error(e);
				}
			}
	       return auth;
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
	        // Grab principal
	    	UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

	        // Create JWT Token
	        String token = JWT.create()
	                .withSubject(principal.getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
	                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
	       Cookie cookie = new Cookie(token,token);
	       listCookies.add(cookie);
	        // Add token in response
	        //response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);
	        ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getOutputStream(), token);
	    }
}
