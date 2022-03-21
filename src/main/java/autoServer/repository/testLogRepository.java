package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import autoServer.Entity.TestLogEntity;

public interface testLogRepository extends JpaRepository<TestLogEntity, Long>{

}
