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
    @Column
    private String configName;
    @Column
    private String configValue;
}
