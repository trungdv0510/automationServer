package autoServer.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import autoServer.Utils.contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regresstion")
public class RegresstionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "testcase_name")
	private String testcaseName;
	@Column(nullable = false)
	private LocalDateTime dateRun = LocalDateTime.now();
	@Column(nullable = false,name = "evidence_link")
	private String evidenceLink;
	@Column(nullable = false)
	private String result;
	@Column
	private String author;
	@Column(name = "error_description")
	private String ErrorDescription;
	@Column
	private String sprint;
	@Column(name = "testsuite_uuid")
	private String testsuiteUuid;
}
