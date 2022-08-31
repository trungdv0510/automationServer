package autoServer.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import autoServer.Utils.contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regresstion")
public class RegresstionEntity extends AbtractEntity {
	@Column(nullable = false)
	private String testcaseName;
	@Column(nullable = false)
	private LocalDateTime dateRun = LocalDateTime.now();
	@Column(nullable = false)
	private String evidenceLink;
	@Column(nullable = false)
	private String result;
	@Column
	private String author;
	@Column
	private String ErrorDescription;
	@Column
	private String sprint;
}
