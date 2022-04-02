package autoServer.Controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

import autoServer.DTO.TestSuiteDTO;
import autoServer.Utils.contains;
import autoServer.services.impl.TestSuiteServices;

@RequestMapping(value = "/api/testsuite")
@RestController
public class testSuiteController {
	@Autowired
	private TestSuiteServices testsuite;

	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<TestSuiteDTO>> findAll() {
		return new ResponseEntity<>(testsuite.findAlls(), HttpStatus.OK);
	}

	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<String> insertSuite(@Valid @RequestBody TestSuiteDTO testSuiteDTO) {
		String result = "FAIL";

		if (testsuite.save(testSuiteDTO)) {
			result = "OK";
		}

		return new ResponseEntity<>(result, contains.configHeader(), HttpStatus.OK);
	}

	@GetMapping(value = "/find/{uuid}")
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
}
