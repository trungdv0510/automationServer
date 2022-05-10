package autoServer.DTO;

import java.util.List;

import lombok.Data;

@Data
public class testSuiteDetails {
	private TestSuiteDTO testSuiteDTO;
	private List<TestCaseDTO> testCaseDTOs;
	private List<List<TestLogDTO>> testLogDTOs;
}
