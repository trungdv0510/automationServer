package autoServer.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autoServer.services.impl.TestSuiteServices;

@RequestMapping(value = "/api/testsuite")
@RestController
public class testSuiteController{
	@Autowired
	private TestSuiteServices testsuite;
	@GetMapping(value = "/all")
	public ResponseEntity<Object> findAll(){
		return new ResponseEntity<>(testsuite.findAlls(),HttpStatus.OK);
	}
}
