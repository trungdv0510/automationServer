package autoServer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDto {
    @NotEmpty(message = "config name is null")
    private String configName;
    @NotEmpty(message = "config value is null")
    private String configValue;
}
