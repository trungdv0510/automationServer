package autoServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "config")
public class ConfigEntity {
    @Id
    @Column(name = "config_name")
    private String configName;
    @Column(name = "config_value")
    private String configValue;
}
