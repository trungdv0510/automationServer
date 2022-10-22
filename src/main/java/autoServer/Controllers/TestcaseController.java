package autoServer.Controllers;

import autoServer.DTO.TestCaseDTO;
import autoServer.Utils.contains;
import autoServer.services.impl.TestcaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TestcaseController {
	@Autowired
	private TestcaseService testcaseService;

	@PostMapping(value = "/testcase", produces = "application/json")
	public ResponseEntity<String> insertTestcase(@Valid @RequestBody TestCaseDTO testcase) {
		String result = "FAIL";
		if (testcaseService.save(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/testcases", produces = "application/json")
	public ResponseEntity<String> insertTestcases(@Valid @RequestBody List<TestCaseDTO> testcase) {
		String result = "FAIL";
		if (testcaseService.saveAll(testcase)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user/testcases/{id}", produces = "application/json")
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
