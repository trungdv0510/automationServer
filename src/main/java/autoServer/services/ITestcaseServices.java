package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestCaseDTO;

public interface ITestcaseServices {
	boolean save(TestCaseDTO testcase);
	boolean delete(long[] id);
	List<TestCaseDTO> findAlls();
	List<TestCaseDTO> findAlls(Pageable page);
	boolean update(TestCaseDTO testcase);
	boolean saveAll(List<TestCaseDTO> testcase);
	List<TestCaseDTO> findByTestSuiteUUID(String uuid);
}
