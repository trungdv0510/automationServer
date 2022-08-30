package autoServer.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestSuiteDTO extends AbtractDTO<TestSuiteDTO>{
	@NotEmpty(message = "suiteName is empty")
	private String suiteName;
	@NotEmpty(message = "dateRun is empty")
	private String dateRun;
	@NotEmpty(message = "runTime is empty")
	private String runTime;
	@NotEmpty(message = "testcasePass is empty")
	private String testcasePass;
	@NotEmpty(message = "testcaseFail is empty")
	private String testcaseFail;
	@NotEmpty(message = "testLogSum  is empty")
	private String testlogSum;
	@NotEmpty(message = "IPName is empty")
	private String ipName;
	@NotEmpty(message = "HostName is empty")
	private String hostName;
	private Integer sprint;
	private boolean isRegresstion;
}
