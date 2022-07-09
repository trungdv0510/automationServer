package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestSuiteDTO;
import autoServer.DTO.UserDTO;

public interface IUserServices {
	public String save(UserDTO user);
	public boolean delete(long[] id);
	public List<UserDTO> findAlls();
	public List<UserDTO> findAlls(Pageable page);
	public boolean update(UserDTO user);
	public boolean login(UserDTO user);
	public boolean logout(String tokens);
}
