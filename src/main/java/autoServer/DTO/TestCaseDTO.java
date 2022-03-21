package autoServer.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestCaseDTO extends AbtractDTO<TestCaseDTO>{
	private String testName;
	private String methodName;
	private String author;
	private String suiteUUID;
	private String startTime;
	private String endTime;
	private String timeDuration;
}
