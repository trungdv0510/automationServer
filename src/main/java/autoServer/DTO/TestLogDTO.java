package autoServer.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestLogDTO extends AbtractDTO<TestLogDTO>{
	private String testcaseUUID;
	private String stepName;
	private String detail;
	private String testLogTime;
	private String imgPath;
	private String videoPath;
}
