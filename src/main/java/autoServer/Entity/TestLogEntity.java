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
	@Column
	private String stepName;
	@Column
	private String result;
	@Column
	private String detail;
	@Column
	private String testLogTime;
	@Column(columnDefinition = "TEXT")
	private String imgPath;
	@Column(columnDefinition = "TEXT")
	private String videoPath;
	@ManyToOne
	@JoinColumn(name = "testcaseUUID",columnDefinition = "TEXT",nullable = false)
	@ToString.Exclude
	private TestCaseEntity testcaseUUID;
}
