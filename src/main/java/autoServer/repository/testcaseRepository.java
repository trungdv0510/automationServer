package autoServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.DTO.TestCaseDTO;
import autoServer.Entity.TestCaseEntity;

public interface testcaseRepository extends JpaRepository<TestCaseEntity, Long>{
	@Query(value = "select * from testcase where suiteUUID = :suiteUUID",nativeQuery = true)
	public List<TestCaseDTO> findByTestSuiteUUID(@Param("suiteUUID") String suiteUUID);
}
