package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import autoServer.Entity.TestCaseEntity;

public interface testcaseRepository extends JpaRepository<TestCaseEntity, Long>{

}
