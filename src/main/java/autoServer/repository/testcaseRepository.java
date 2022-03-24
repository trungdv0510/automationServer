package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.DTO.TestCaseDTO;
import autoServer.Entity.TestCaseEntity;

public interface testcaseRepository extends JpaRepository<TestCaseEntity, Long>{
	@Query(value = "select * from testcase where suiteUUID = :suiteUUID",nativeQuery = true)
	public TestCaseDTO findByTestSuiteUUID(@Param("suiteUUID") String suiteUUID);
}
