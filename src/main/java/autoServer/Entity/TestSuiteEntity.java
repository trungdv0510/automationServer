package autoServer.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name="testsuite")
public class TestSuiteEntity extends abtractEntity{
	@Column
	private String SuiteName;
	@Column
	private String dateRun;
	@Column
	private String result;
	@Column
	private String runTime;
	@Column
	private String testcasePass;
	@Column
	private String testcaseFail;
	@Column
	private String methodPass;
	@Column
	private String methodFail;
	
	@OneToMany(mappedBy = "suiteUUID", cascade = CascadeType.ALL)
	Collection<TestCaseEntity> testcases;
}
