package autoServer.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;

import autoServer.DTO.UserPrincipal;
import autoServer.Entity.UserEntity;
import autoServer.repository.userRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	@Autowired
	private userRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, userRepository user) {
		super(authenticationManager);
		this.userRepository = user;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Read the Authorization header, where the JWT token should be
		String header = request.getHeader(JwtProperties.HEADER_STRING);

		// If header does not contain BEARER or is null delegate to Spring impl and exit
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		// If header is present, try grab user principal from database and perform
		// authorization
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Continue filter execution
		chain.doFilter(request, response);
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		try {
			int count = 0;
			String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
			if (token != null) {
				// check cookie 
				for (Cookie item: JwtAuthenticationFilter.listCookies) {
					if (item.getName().contains(token)) {
						count++;
						break;
					}
				}
				if (count>=1) {
					// parse the token and validate it
					String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token)
							.getSubject();

					// Search in the DB if we find the user by token subject (username)
					// If so, then grab user details and create spring auth token using username,
					// pass, authorities/roles
					if (userName != null) {
						UserEntity userE = userRepository.findByUsername(userName);
						UserPrincipal principal = new UserPrincipal(userE);
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
								principal.getAuthorities());
						return auth;
					}
				}
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			logger.error(e);
		}
		return null;
	}
}
