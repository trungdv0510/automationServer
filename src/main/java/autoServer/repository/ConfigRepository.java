package autoServer.repository;

import autoServer.Entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity,Long> {
    @Query(value = "SELECT config_value FROM config WHERE config_name = :name",nativeQuery = true)
    String getConfigValue(@Param("name") String configName);

}
