package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import autoServer.Entity.TestSuiteEntity;

public interface testSuiteRepository extends JpaRepository<TestSuiteEntity, Long>{
}
