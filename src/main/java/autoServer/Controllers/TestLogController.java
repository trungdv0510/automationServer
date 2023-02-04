package autoServer.Controllers;

import autoServer.DTO.TestLogDTO;
import autoServer.Utils.contains;
import autoServer.services.ITestLogServices;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TestLogController {
	@Autowired
	private ITestLogServices testlogService;

	@PostMapping(value = "/testlog", produces = "application/json")
	public ResponseEntity<String> insertTestcase(@Valid @RequestBody TestLogDTO testLog) {
		String result = "FAIL";
		if (testlogService.save(testLog)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/testlogs", produces = "application/json")
	public ResponseEntity<String> insertTestcases(@Valid @RequestBody List<TestLogDTO> testLogs) {
		String result = "FAIL";
		if (testlogService.saveAll(testLogs)) {
			result = "OK";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user/testlogs/{testcaseUUID}", produces = "application/json")
	public ResponseEntity<?> getListTestLogWithTestCaseUUID(@PathVariable(value = "testcaseUUID") @NotBlank(message = "ID is not null") String testcaseUUID) {
		List<TestLogDTO> testLogList = new ArrayList<TestLogDTO>();
		HttpStatus status = HttpStatus.OK;
		if (!StringUtils.isBlank(testcaseUUID)) {
			testLogList = testlogService.findAllTestLogsWithTestcaseUUID(testcaseUUID);
			if (testLogList.size()<=0) {
				status = HttpStatus.NOT_FOUND;
				return new ResponseEntity<>("Not found test log has "+testcaseUUID,contains.configHeader(), status);
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testLogList,contains.configHeader(), status);
	}
	
	@GetMapping(value = "/user/testlogs/{id}",produces = "application/json")
	public ResponseEntity<Object> getTestLogWithId(@PathVariable(value = "id") @NotBlank String id) {
		Object testLog = "Not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		if (StringUtils.isBlank(id)) {
			testLog = testlogService.findOneByID(id);
			if (testLog != null) {
				status = HttpStatus.OK;
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(testLog,contains.configHeader(), status);
	}
	@PostMapping(value = "/img")
	public ResponseEntity<String> saveImg(@RequestParam("fileName") MultipartFile file){
		System.out.println("img file =>>>>>>>>>>>>> "+file);
		String result = testlogService.saveImgOrVideo(file);
		HttpStatus status = HttpStatus.OK;
		if (result.contains("Error")) {
			status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<>(result, status);
	}
	@PostMapping(value = "/video")
	public ResponseEntity<String> saveVideo(@RequestParam("fileName") MultipartFile file){
		System.out.println("video file =>>>>>>>>>>>>> "+file);
		String result = testlogService.saveImgOrVideo(file);
		HttpStatus status = HttpStatus.OK;
		if (result.contains("Error")) {
			status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<>(result, status);
	}


}
