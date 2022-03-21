package autoServer.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "testcase")
public class TestCaseEntity extends abtractEntity{
	@Column
	private String testName;
	@Column
	private String methodName;
	@Column
	private String result;
	@Column
	private String author;
	@Column
	private String startTime;
	@Column
	private String endTime;
	@Column
	private String timeDuration;
	@ManyToOne
	@JoinColumn(name = "suiteUUID",nullable = false,columnDefinition = "text")
	@ToString.Exclude
	private TestSuiteEntity suiteUUID;
	
	@OneToMany(mappedBy = "testcaseUUID",cascade = CascadeType.ALL)
	Collection<TestLogEntity> testLogs;

}
