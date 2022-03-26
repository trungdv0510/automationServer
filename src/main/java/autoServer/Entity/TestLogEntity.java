package autoServer.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class TestLogEntity extends abtractEntity{
	@Column(nullable = false)
	private String stepName;
	@Column(nullable = false)
	private String result;
	@Column(nullable = false)
	private String detail;
	@Column(nullable = false)
	private String testLogTime;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String imgPath;
	@Column(columnDefinition = "TEXT",nullable = false)
	private String videoPath;
	@ManyToOne
	@JoinColumn(name = "testcaseUUID",columnDefinition = "TEXT",nullable = false)
	@ToString.Exclude
	private TestCaseEntity testcaseUUID;
}
