package autoServer.DTO;

import autoServer.Utils.contains;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SqlResultSetMapping(
        name = contains.ResultSetMapping.REGRESSTION_MAPPTING,
        classes = {
                @ConstructorResult(
                        targetClass = RegresstionDto.class,
                        columns = {
                                @ColumnResult(name = "testcaseName", type = Long.class),
                                @ColumnResult(name = "dateRun", type = LocalDateTime.class),
                                @ColumnResult(name = "evidenceLink", type = String.class),
                                @ColumnResult(name = "monthRunScript", type = Integer.class),
                                @ColumnResult(name = "author", type = String.class),
                                @ColumnResult(name = "ErrorDecription", type = String.class),
                                @ColumnResult(name = "sprint", type = String.class)
                        }
                )
        }
)
public class RegresstionDto {
    @NotEmpty(message = "testcase name is null")
    private String testcaseName;
    @NotEmpty(message = "date run is null")
    private LocalDateTime dateRun;
    @NotEmpty(message = "link evidence is null")
    private String evidenceLink;
    @NotEmpty(message = "result is null")
    private String result;
    @NotEmpty(message = "author is null")
    private String author;
    @NotEmpty(message = "error description is null")
    private String ErrorDescription;
    @NotEmpty(message = "sprint is null")
    private String sprint;
}
