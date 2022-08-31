package autoServer.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestSuiteDTO;
import autoServer.DTO.requestData;
import autoServer.DTO.testSuiteDetails;

public interface ITestSuiteServices {
	boolean save(TestSuiteDTO testsuite);
	boolean delete(long[] id);
	List<TestSuiteDTO> findAlls();
	List<TestSuiteDTO> findAlls(Pageable page);
	boolean update(TestSuiteDTO testsuite);
	List<Integer> getCountPassFail();
	testSuiteDetails findOneByUUID(String uuid);
	List<TestSuiteDTO> getTestSuiteDTOByDate(String startDate , String endDate);
}
