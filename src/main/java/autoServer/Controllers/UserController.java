package autoServer.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autoServer.DTO.UserDTO;
import autoServer.services.IUserServices;
import autoServer.services.impl.UserService;
import lombok.val;

@RestController
@RequestMapping(value = "api/")
public class UserController {
	@Autowired
	private UserService userServices;
	@PostMapping(value = "admin/user")
	public ResponseEntity<String> insertUser(@Valid @RequestBody UserDTO userDTO){
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String result = userServices.save(userDTO);
		if (result.contains("Pass")) {
			status = HttpStatus.OK;
		}
		else if(result.contains("Duplicate")){
			status = HttpStatus.CONFLICT;
		}
		else {
			status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<>(result, status);
	}
	
}
