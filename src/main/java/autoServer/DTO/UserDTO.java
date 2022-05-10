package autoServer.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class UserDTO {
	@NotEmpty(message = "userName is empty")
	private String username;
	@NotEmpty(message = "password is empty")
	private String password;
	@Email(message = "Is not type of email")
	@NotEmpty(message = "email is empty")
	private String email;
	@NotEmpty(message = "permission is empty")
	private String permissions = "";
	@NotEmpty(message = "Role is empty")
	private String roles = "";
	private boolean active = true;
}
