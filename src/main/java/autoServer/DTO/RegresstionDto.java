package autoServer.DTO;

import autoServer.Utils.contains;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegresstionDto implements Serializable {
    private Long id;
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
    private String errorDescription;
    @NotEmpty(message = "sprint is null")
    private String sprint;
    private String testsuiteUuid;

    public RegresstionDto(Long id, String testcaseName, LocalDateTime dateRun,
                          String evidenceLink, String result, String author,
                          String ErrorDescription, String sprint, String testsuiteUuid) {
        this.id = id;
        this.testcaseName= testcaseName;
        this.dateRun = dateRun;
        this.evidenceLink = evidenceLink;
        this.result = result;
        this.author = author;
        this.errorDescription = ErrorDescription;
        this.sprint = sprint;
        this.testsuiteUuid = testsuiteUuid;
    }
}
