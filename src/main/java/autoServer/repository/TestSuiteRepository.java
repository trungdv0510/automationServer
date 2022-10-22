package autoServer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.Entity.TestSuiteEntity;

public interface TestSuiteRepository extends JpaRepository<TestSuiteEntity, Long>{
	@Query(value = "select * from testsuite where result = :resuit",nativeQuery = true)
    int getCountTestSuitePassOrFail(@Param("resuit") String resuil);
	
	@Query(value = "select * from testsuite where create_date >= :startDate and create_date <= :endDate",nativeQuery = true)
    List<TestSuiteEntity> getTestSuiteByDateStartAndDateEnd(@Param("startDate") Date startDate, @Param("endDate")Date endDate);
	
	@Query(value = " SELECT * FROM testsuite where uuid = :uuid",nativeQuery = true)
    TestSuiteEntity findOneByUUID(@Param("uuid") String uuid);
	
}
