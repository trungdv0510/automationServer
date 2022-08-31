package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestSuiteDTO;
import autoServer.DTO.UserDTO;

public interface IUserServices {
	String save(UserDTO user);
	boolean delete(long[] id);
	List<UserDTO> findAlls();
	List<UserDTO> findAlls(Pageable page);
	boolean update(UserDTO user);
	boolean login(UserDTO user);
	boolean logout(String tokens);
}
