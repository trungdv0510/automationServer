package autoServer.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDTO{
	@NotEmpty(message = "userName is empty")
	private String userName;
	@NotEmpty(message = "password is empty")
	private String password;
	@Email(message = "Is not type of email")
	@NotEmpty(message = "email is empty")
	private String email;
	@NotEmpty(message = "permission is empty")
	private String permission;
}
