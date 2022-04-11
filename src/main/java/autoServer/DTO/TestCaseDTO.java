package autoServer.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestCaseDTO extends AbtractDTO<TestCaseDTO>{
	@NotEmpty(message = "Test name is empty")
	private String testName;
	@NotEmpty(message = "method name is empty")
	private String methodName;
	@NotEmpty(message = "author is empty")
	private String author;
	@NotEmpty(message = "suite uuid is empty")
	private String suiteUUID;
	@NotEmpty(message = "start time is empty")
	private String startTime;
	@NotEmpty(message = "end time is empty")
	private String endTime;
	@NotEmpty(message = "time duration is empty")
	private String timeDuration;
}
