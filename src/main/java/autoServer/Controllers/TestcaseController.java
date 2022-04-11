package autoServer.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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

import autoServer.DTO.TestCaseDTO;
import autoServer.Utils.contains;
import autoServer.services.impl.TestcaseService;

@RestController
@RequestMapping(value = "/api/testcase")
public class TestcaseController {
	@Autowired
	private TestcaseService testcaseService;

	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<String> insertTestcase(@Valid @RequestBody TestCaseDTO testcase) {
		String result = "FAIL";
		if (testcaseService.save(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/adds", produces = "application/json")
	public ResponseEntity<String> insertTestcases(@Valid @RequestBody List<TestCaseDTO> testcase) {
		String result = "FAIL";
		if (testcaseService.saveAll(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getTestcase(@PathVariable(value = "id") @NotEmpty(message = "id is not null") String uuid) {
		List<TestCaseDTO> testCase = new ArrayList<TestCaseDTO>();
		HttpStatus status = HttpStatus.OK;
		if (!StringUtils.isBlank(uuid)) {
			 testCase = testcaseService.findByTestSuiteUUID(uuid);
			 if (testCase.size() <= 0) {
				status = HttpStatus.NOT_FOUND;
				return new ResponseEntity<>("Not Found "+uuid, contains.configHeader(), status);
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testCase, contains.configHeader(), status);
	}
}
