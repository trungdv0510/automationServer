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
public class TestSuiteEntity extends AbtractEntity {
	@Column(nullable = false)
	private String suiteName;
	@Column(nullable = false)
	private String dateRun;
	@Column(nullable = false)
	private String result;
	@Column(nullable = false)
	private String runTime;
	@Column(nullable = false)
	private String testcasePass;
	@Column(nullable = false)
	private String testcaseFail;
	@Column(nullable = false)
	private String testlogSum;
	@Column(nullable = false)
	private String ipName;
	@Column(nullable = false)
	private String hostName;
	@Column
	private Integer sprint;
	@OneToMany(mappedBy = "suiteuuid", cascade = CascadeType.ALL)
	Collection<TestCaseEntity> testcases;
}
