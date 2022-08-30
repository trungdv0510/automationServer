package autoServer.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="testlog")
public class TestLogEntity extends AbtractEntity {
	@Column(nullable = false)
	private String stepName;
	@Column(nullable = false)
	private String result;
	@Column(nullable = false)
	private String detail;
	@Column(nullable = false)
	private String testLogTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testcaseuuid",nullable = false,columnDefinition = "TEXT", referencedColumnName = "uuid")
	@ToString.Exclude
	private TestCaseEntity testcaseuuid;
}
