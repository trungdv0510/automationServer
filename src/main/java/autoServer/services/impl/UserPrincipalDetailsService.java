package autoServer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import autoServer.DTO.UserPrincipal;
import autoServer.Entity.UserEntity;
import autoServer.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = this.repository.findByUsername(username);
		return new UserPrincipal(user);
	}

}
