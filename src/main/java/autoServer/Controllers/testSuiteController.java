package autoServer.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autoServer.DTO.TestLogDTO;
import autoServer.DTO.TestSuiteDTO;
import autoServer.Utils.contains;
import autoServer.services.impl.TestSuiteServices;

@RequestMapping(value = "/api/testsuite")
@RestController
public class testSuiteController{
	@Autowired
	private TestSuiteServices testsuite;
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<TestSuiteDTO>> findAll(){
		return new ResponseEntity<>(testsuite.findAlls(),HttpStatus.OK);
	}
	@PostMapping(value = "/add/testsute")
	public ResponseEntity<String> insertSuite(@RequestBody TestSuiteDTO testSuiteDTO) {
		String result = "FAIL";
		if(testsuite.save(testSuiteDTO)) {
			result = "OK";
		}
		return new ResponseEntity<>(result,contains.configHeader(),HttpStatus.OK);
	}
	@GetMapping(value = "/find/testsuite/{uuid}", produces = "application/json")
	public ResponseEntity<TestSuiteDTO> fineOne(@PathVariable String uuid){
		TestSuiteDTO testsuiteDTO =  null;
		HttpStatus status = HttpStatus.OK;
		if(!StringUtils.isBlank(uuid)) {
			testsuiteDTO = testsuite.findOneByUUID(uuid);
			if (testsuiteDTO == null) {
				status = HttpStatus.NOT_FOUND;
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testsuiteDTO,contains.configHeader(),status);
	}
}
