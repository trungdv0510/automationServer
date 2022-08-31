package autoServer.repository;

import autoServer.Entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfigRepository extends JpaRepository<ConfigEntity,Long> {
    @Query(value = "SELECT configValue FROM config WHERE configName = :name",nativeQuery = true)
    String getConfigValue(@Param("name") String configName);

}
