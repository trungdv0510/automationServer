package autoServer.Controllers;

import autoServer.DTO.TestSuiteDTO;
import autoServer.Utils.contains;
import autoServer.services.impl.TestSuiteServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@RequestMapping(value = "/api")
@RestController
public class testSuiteController {
	@Autowired
	private TestSuiteServices testsuite;

	@GetMapping(value = "/user/testsuites", produces = "application/json")
	public ResponseEntity<List<TestSuiteDTO>> findAll() {
		return new ResponseEntity<>(testsuite.findAlls(), HttpStatus.OK);
	}

	@PostMapping(value = "/testsuite", produces = "application/json")
	public ResponseEntity<String> insertSuite(@Valid @RequestBody TestSuiteDTO testSuiteDTO) {
		String result = "FAIL";

		if (testsuite.save(testSuiteDTO)) {
			result = "OK";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user/testsuites/{uuid}")
	public ResponseEntity<Object> fineOne(@PathVariable(value = "uuid")  @NotBlank(message = "id is not null") String uuid) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Object testsuiteDTO = "Not found";
		if (!StringUtils.isBlank(uuid.toString())) {
			testsuiteDTO = testsuite.findOneByUUID(uuid);
			if (testsuiteDTO != null) {
				status = HttpStatus.OK;
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testsuiteDTO, contains.configHeader(), status);
	}
	
	@GetMapping(value = "/user/testsuites/findbydate")
	public ResponseEntity<Object> findTestSuiteByDate(@Valid @RequestParam(name = "startdate") String startDate, @RequestParam(name = "enddate") String enddate){
		List<TestSuiteDTO> testsuiteDTO = new LinkedList<>();
		if (!StringUtils.isBlank(enddate) && !StringUtils.isBlank(startDate)) {
			testsuiteDTO = testsuite.getTestSuiteDTOByDate(startDate,enddate);
		}
		return new ResponseEntity<>(testsuiteDTO, contains.configHeader(), HttpStatus.OK);
		
	}
}
