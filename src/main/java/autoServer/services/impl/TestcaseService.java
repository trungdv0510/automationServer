package autoServer.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import autoServer.Converter.TestCaseMapping;
import autoServer.DTO.TestCaseDTO;
import autoServer.Entity.TestCaseEntity;
import autoServer.Entity.TestSuiteEntity;
import autoServer.repository.testcaseRepository;
import autoServer.services.ITestcaseServices;

@Service
public class TestcaseService implements ITestcaseServices{

	@Autowired
	private TestCaseMapping mapping;
	@Autowired
	private testcaseRepository testcaseRepository;
	@Override
	public boolean save(TestCaseDTO testcase) {
		boolean result = false;
		try {
			if (testcase != null) {
				TestCaseEntity testcaseNew = mapping.toEntity(testcase);
				testcaseRepository.saveAndFlush(testcaseNew);
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		return result;
	}

	@Override
	public boolean delete(long[] id) {
		boolean result = false;
		try {
			if (id != null && id.length>0) {
				for (long l : id) {
					testcaseRepository.deleteById(l);
				}
				result = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		return result;
	}

	@Override
	public List<TestCaseDTO> findAlls() {
		List<TestCaseDTO> testcaseDTOs = new ArrayList<TestCaseDTO>();
		try {
			testcaseDTOs = testcaseRepository.findAll().stream()
							.map(i -> mapping.toDTO(i))
							.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testcaseDTOs;
	}

	@Override
	public List<TestCaseDTO> findAlls(Pageable page) {
		List<TestCaseDTO> testcaseDTOs = new ArrayList<TestCaseDTO>();
		try {
			if (page.isPaged()) {
				testcaseDTOs = testcaseRepository.findAll(page).stream().map(i -> mapping.toDTO(i))
						.collect(Collectors.toList());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testcaseDTOs;
	}

	@Override
	public boolean update(TestCaseDTO testsuite) {
		boolean result = false;
		try {
			if (testsuite != null) {
				TestCaseEntity testcase = testcaseRepository.findById(testsuite.getId()).get();
				testcase.setMethodName(testsuite.getMethodName());
				testcase.setTestName(testsuite.getTestName());
				testcase.setResult(testsuite.getResult());
				testcaseRepository.saveAndFlush(testcase);
				result = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean saveAll(List<TestCaseDTO> testcase) {
		boolean result = false;
		try {
			if (testcase!=null) {
				long number = testcase.stream().map(i -> testcaseRepository.saveAndFlush(mapping.toEntity(i))).count();
				if ((int) number == testcase.size()) {
					result = true;
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TestCaseDTO> findByTestSuiteUUID(String uuid) {
		List<TestCaseDTO> testCase = null;
		try {
			if (!StringUtils.isBlank(uuid)) {
				testCase = testcaseRepository.findByTestSuiteUUID(uuid);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return testCase;
	}

}
