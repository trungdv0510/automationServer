package autoServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.Entity.TestCaseEntity;

public interface TestcaseRepository extends JpaRepository<TestCaseEntity, Long>{
	@Query(value = "select * from testcase where suiteUUID = :suiteUUID",nativeQuery = true)
	public List<TestCaseEntity> findByTestSuiteUUID(@Param("suiteUUID") String suiteUUID);
	
	@Query(value = "select * from testcase where uuid = :suiteUUID",nativeQuery = true)
	public TestCaseEntity findByUUID(@Param("suiteUUID") String uuid);
}
