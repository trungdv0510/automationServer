package autoServer.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestSuiteDTO extends AbtractDTO<TestSuiteDTO>{
	private String SuiteName;
	private String dateRun;
	private String runTime;
	private String testcasePass;
	private String testcaseFail;
	private String methodPass;
	private String methodFail;
}
