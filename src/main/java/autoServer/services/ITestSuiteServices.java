package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestSuiteDTO;

public interface ITestSuiteServices {
	public boolean save(TestSuiteDTO testsuite);
	public boolean delete(long[] id);
	public List<TestSuiteDTO> findAlls();
	public List<TestSuiteDTO> findAlls(Pageable page);
	public boolean update(TestSuiteDTO testsuite);
	public List<Integer> getCountPassFail();
	public TestSuiteDTO findOneByUUID(String uuid);
}
