package autoServer.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "regresstion")
public class RegresstionEntity extends abtractEntity{
	@Column(nullable = false)
	private String testcaseName;
	@Column(nullable = false)
	private long dateRun = new Date().getTime();
	@Column(nullable = false)
	private String evidenceLink;
	@Column(nullable = false)
	private String result;
	@Column(nullable = false)
	private int monthRunScript = new Date().getMonth();
	@Column
	private String author;
	@Column
	private String ErrorDecription;
}
