package autoServer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import autoServer.Converter.UserMapping;
import autoServer.DTO.UserDTO;
import autoServer.DTO.UserPrincipal;
import autoServer.Entity.UserEntity;
import autoServer.repository.userRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	@Autowired
	private userRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = this.repository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
