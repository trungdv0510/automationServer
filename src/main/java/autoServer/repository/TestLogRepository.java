package autoServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.Entity.TestLogEntity;

public interface TestLogRepository extends JpaRepository<TestLogEntity, Long>{
	@Query(value = "SELECT * FROM testlog WHERE testcaseUUID = :testcaseUUID",nativeQuery = true)
	public List<TestLogEntity> findAllTestWithTestCaseUUID(@Param("testcaseUUID") String uuid);
	
	@Query(value = "SELECT * FROM testlog WHERE id = :id",nativeQuery = true)
	public TestLogEntity findOneById(@Param("id") Long id);
}
