package autoServer.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import autoServer.DTO.RegresstionDto;
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
@SqlResultSetMapping(
		name = contains.ResultSetMapping.REGRESSTION_MAPPTING,
		classes = {
				@ConstructorResult(
						targetClass = RegresstionDto.class,
						columns = {
								@ColumnResult(name = "id", type = Long.class),
								@ColumnResult(name = "ErrorDescription", type = String.class),
								@ColumnResult(name = "author", type = String.class),
								@ColumnResult(name = "dateRun", type = LocalDateTime.class),
								@ColumnResult(name = "evidenceLink", type = String.class),
								@ColumnResult(name = "result", type = String.class),
								@ColumnResult(name = "sprint", type = String.class),
								@ColumnResult(name = "testcaseName", type = String.class),
								@ColumnResult(name = "testsuiteUuid", type = String.class)
						}
				)
		}
)
public class RegresstionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "testcase_name")
	private String testcaseName;
	@Column(nullable = false, name = "date_run")
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
