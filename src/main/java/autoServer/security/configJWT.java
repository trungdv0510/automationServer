package autoServer.security;

import autoServer.Utils.contains;
import autoServer.repository.UserRepository;
import autoServer.services.impl.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configurable
@EnableWebSecurity
public class configJWT extends WebSecurityConfigurerAdapter {
	private final UserPrincipalDetailsService userService;
	 private final UserRepository userRepository;
	public configJWT(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository use) {
		this.userService = userPrincipalDetailsService;
		this.userRepository = use;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http     .cors().and()
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // add jwt filters (1. authentication, 2. authorization)
               .addFilter(new JwtAuthenticationFilter(authenticationManager()))
               .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
               .authorizeRequests()
                // configure access rules
               .antMatchers(HttpMethod.POST, "/login").permitAll()
               .antMatchers("/api/management/*").hasRole(contains.roleManager)
               .antMatchers("/api/admin/*").hasRole(contains.roleAdmin)
               .antMatchers("/api/user/*").hasRole(contains.roleUser);
    }
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userService);
		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() 
	  {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	  }
}
