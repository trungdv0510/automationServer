package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import autoServer.DTO.TestCaseDTO;

public interface ITestcaseServices {
	public boolean save(TestCaseDTO testcase);
	public boolean delete(long[] id);
	public List<TestCaseDTO> findAlls();
	public List<TestCaseDTO> findAlls(Pageable page);
	public boolean update(TestCaseDTO testcase);
	public boolean saveAll(List<TestCaseDTO> testcase);
}
