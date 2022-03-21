package autoServer.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDTO{
	private String userName;
	private String password;
	private String email;
	private String permission;
}
