package autoServer.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class TestCaseEntity extends AbtractEntity {
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "suiteuuid", referencedColumnName = "uuid" )
	@ToString.Exclude
	private TestSuiteEntity suiteuuid;
	
	@OneToMany(mappedBy = "testcaseuuid",cascade = CascadeType.ALL)
	Collection<TestLogEntity> testLogs;

}
