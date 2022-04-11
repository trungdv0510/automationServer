package autoServer.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestLogDTO extends AbtractDTO<TestLogDTO>{
	@NotEmpty(message = "testcaseUUID is empty")
	private String testcaseUUID;
	@NotEmpty(message = "step Name is empty")
	private String stepName;
	private String detail;
	@NotEmpty(message = "testLog Time is empty")
	private String testLogTime;
	
}
