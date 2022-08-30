package autoServer.DTO;

import autoServer.Utils.contains;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
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
    private String testcaseName;
    private LocalDateTime dateRun;
    private String evidenceLink;
    private String result;
    private Integer monthRunScript;
    private String author;
    private String ErrorDecription;
    private String sprint;
}
