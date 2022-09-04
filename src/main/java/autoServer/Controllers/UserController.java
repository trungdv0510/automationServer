package autoServer.Controllers;

import autoServer.DTO.UserDTO;
import autoServer.security.JwtProperties;
import autoServer.services.impl.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/")
public class UserController {
	@Autowired
	private UserService userServices;
	@PostMapping(value = "admin/user")
	public ResponseEntity<String> insertUser(@Valid @RequestBody UserDTO userDTO){
		HttpStatus status= HttpStatus.BAD_GATEWAY;;
		String result = userServices.save(userDTO);
		if (result.contains("Pass")) {
			status = HttpStatus.OK;
		}
		else if(result.contains("Duplicate")){
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(result, status);
	}
	@GetMapping(value = "logout")
	public ResponseEntity<String> logout(HttpServletRequest request){
		String responeString = "logout fail";
		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		if (!StringUtils.isBlank(token)) {
			if (userServices.logout(token)) {
				responeString = "Logout success";
			}
		}
		else {
			responeString = "Not login";
		}
		
		  
	     return new ResponseEntity<>(responeString, HttpStatus.OK);
	}
}
