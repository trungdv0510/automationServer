package autoServer.Controllers;

import java.util.ArrayList;
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
import autoServer.Utils.contains;
import autoServer.services.ITestLogServices;

@RestController
@RequestMapping(value = "/api/testlog")
public class TestLogController {
	@Autowired
	private ITestLogServices testlogService;

	@PostMapping(value = "/add/testlog", produces = "application/json")
	public ResponseEntity<String> insertTestcase(@RequestBody TestLogDTO testLog) {
		String result = "FAIL";
		if (testlogService.save(testLog)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/add/testlogs", produces = "application/json")
	public ResponseEntity<String> insertTestcases(@RequestBody List<TestLogDTO> testLogs) {
		String result = "FAIL";
		if (testlogService.saveAll(testLogs)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/get/testlogs/{testcaseUUID}", produces = "application/json")
	public ResponseEntity<List<TestLogDTO>> getListTestLogWithTestCaseUUID(@PathVariable String testcaseUUID) {
		List<TestLogDTO> testLogList = new ArrayList<TestLogDTO>();
		HttpStatus status = HttpStatus.OK;
		if (!StringUtils.isBlank(testcaseUUID)) {
			testLogList = testlogService.findAllTestLogsWithTestcaseUUID(testcaseUUID);
			if (testLogList == null) {
				status = HttpStatus.NOT_FOUND;
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testLogList,contains.configHeader(), status);
	}
	
	@GetMapping(value = "/get/testlog/{id}",produces = "application/json")
	public ResponseEntity<TestLogDTO> getTestLogWithId(@PathVariable Long id) {
		TestLogDTO testLog= new TestLogDTO();
		HttpStatus status = HttpStatus.OK;
		if (id<=0) {
			testLog = testlogService.findOneByID(id);
			if (testLog == null) {
				status = HttpStatus.NOT_FOUND;
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testLog,contains.configHeader(), status);
	}
}
