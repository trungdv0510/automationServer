package autoServer.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import autoServer.Converter.UserMapping;
import autoServer.DTO.TestCaseDTO;
import autoServer.DTO.UserDTO;
import autoServer.Entity.TestSuiteEntity;
import autoServer.Entity.UserEntity;
import autoServer.repository.userRepository;
import autoServer.services.IUserServices;
@Service
public class UserService implements IUserServices{

	@Autowired
	private UserMapping mapping;
	@Autowired
	private userRepository repository;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	@Override
	public String save(UserDTO user) {
		String result = "Fail";
		try {
			UserEntity userEntity = mapping.toEntity(user);
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			List<UserDTO> listUser = findAlls();
			long count =  listUser.stream().filter(i -> i.getUsername().contains(user.getUsername())).count();
			if (count<=0) {
				repository.save(userEntity);
				 result = "Pass";
			}
			else {
				result = "Duplicate";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean delete(long[] id) {
		boolean result = false;
		try {
			for (long item : id) {
				repository.deleteById(item);
			}
			result = true;
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UserDTO> findAlls() {
		List<UserDTO> testcaseDTOs = new ArrayList<UserDTO>();
		try {
			if (repository.findAll() != null) {
				testcaseDTOs = repository.findAll().stream().map(i -> mapping.toDTO(i))
						.collect(Collectors.toList());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testcaseDTOs;
	}

	@Override
	public List<UserDTO> findAlls(Pageable page) {
		List<UserDTO> testcaseDTOs = new ArrayList<UserDTO>();
		try {
			if (page.isPaged()) {
				testcaseDTOs = repository.findAll(page).stream().map(i -> mapping.toDTO(i))
						.collect(Collectors.toList());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testcaseDTOs;
	}

	@Override
	public boolean update(UserDTO user) {
		boolean result = false;
		try {
			UserEntity userEntity = repository.findByUsername(user.getUsername());
			if (userEntity != null) {
				userEntity = mapping.toEntity(user);
				userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
				repository.save(userEntity);
				result = true;
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean login(UserDTO user) {
		// TODO Auto-generated method stub
		return false;
	}	

}
