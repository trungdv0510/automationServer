package autoServer.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestSuiteDTO;
import autoServer.DTO.requestData;
import autoServer.DTO.testSuiteDetails;

public interface ITestSuiteServices {
	public boolean save(TestSuiteDTO testsuite);
	public boolean delete(long[] id);
	public List<TestSuiteDTO> findAlls();
	public List<TestSuiteDTO> findAlls(Pageable page);
	public boolean update(TestSuiteDTO testsuite);
	public List<Integer> getCountPassFail();
	public testSuiteDetails findOneByUUID(String uuid);
	public List<TestSuiteDTO> getTestSuiteDTOByDate(String startDate , String endDate);
}
