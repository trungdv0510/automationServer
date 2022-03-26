package autoServer.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "testcase")
public class TestCaseEntity extends abtractEntity{
	@Column(nullable = false)
	private String testName;
	@Column(nullable = false)
	private String methodName;
	@Column(nullable = false)
	private String result;
	@Column(nullable = false)
	private String author;
	@Column(nullable = false)
	private String startTime;
	@Column(nullable = false)
	private String endTime;
	@Column(nullable = false)
	private String timeDuration;
	@ManyToOne
	@JoinColumn(name = "suiteUUID",nullable = false,columnDefinition = "text")
	@ToString.Exclude
	private TestSuiteEntity suiteUUID;
	
	@OneToMany(mappedBy = "testcaseUUID",cascade = CascadeType.ALL)
	Collection<TestLogEntity> testLogs;

}
