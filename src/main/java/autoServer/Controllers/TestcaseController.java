package autoServer.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autoServer.DTO.TestCaseDTO;
import autoServer.services.impl.TestcaseService;

@RestController
@RequestMapping(value = "/api/testcase")
public class TestcaseController {
	@Autowired
	private TestcaseService testcaseService;
	@PostMapping(value = "/add/testcase")
	public ResponseEntity<Object> insertTestcase(@RequestBody TestCaseDTO testcase) {
		String result = "FAIL";
		if(testcaseService.save(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@PostMapping(value = "/add/testcases")
	public ResponseEntity<Object> insertTestcases(@RequestBody List<TestCaseDTO> testcase) {
		String result = "FAIL";
		if(testcaseService.saveAll(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
