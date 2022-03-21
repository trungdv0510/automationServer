package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.UserDTO;
import autoServer.Entity.UserEntity;

@Component
public class UserMapping{
	@Autowired
	private ModelMapper mapper ;
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = null;
		try {
			dto = mapper.map(entity, UserDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return dto;
	}

	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = null;
		try {
			dto = mapper.map(entity, UserDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return entity;
	}
}
