package autoServer.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autoServer.DTO.TestLogDTO;
import autoServer.services.ITestLogServices;

@RestController
@RequestMapping(value = "/api/testlog")
public class TestLogController {
	@Autowired
	private ITestLogServices testcaseService;
	@PostMapping(value = "/add/testlog")
	public ResponseEntity<Object> insertTestcase(@RequestBody TestLogDTO testLog) {
		String result = "FAIL";
		if(testcaseService.save(testLog)) {
			result = "OK";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@PostMapping(value = "/add/testlogs")
	public ResponseEntity<Object> insertTestcases(@RequestBody List<TestLogDTO> testLogs) {
		String result = "FAIL";
		if(testcaseService.saveAll(testLogs)) {
			result = "OK";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
