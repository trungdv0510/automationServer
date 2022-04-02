package autoServer.DTO;

import java.util.UUID;

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
	@NotEmpty(message = "detail is empty")
	private String detail;
	@NotEmpty(message = "testLog Time is empty")
	private String testLogTime;
	@NotEmpty(message = "img Path is empty")
	private String imgPath;
	@NotEmpty(message = "video Path is empty")
	private String videoPath;
	
}
