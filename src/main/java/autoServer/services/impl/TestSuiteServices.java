package autoServer.services.impl;

import autoServer.Converter.TestCaseMapping;
import autoServer.Converter.TestLogMapping;
import autoServer.Converter.TestSuiteMapping;
import autoServer.DTO.TestCaseDTO;
import autoServer.DTO.TestLogDTO;
import autoServer.DTO.TestSuiteDTO;
import autoServer.DTO.testSuiteDetails;
import autoServer.Entity.TestCaseEntity;
import autoServer.Entity.TestSuiteEntity;
import autoServer.Utils.contains;
import autoServer.repository.RegresstionRepository;
import autoServer.repository.TestLogRepository;
import autoServer.repository.TestSuiteRepository;
import autoServer.repository.TestcaseRepository;
import autoServer.services.ITestSuiteServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestSuiteServices implements ITestSuiteServices {
	private static final Logger LOGGER = LogManager.getLogger();
	@Autowired
	private TestSuiteMapping mapping;
	@Autowired
	private TestCaseMapping mappingTestcase;
	@Autowired
	private TestLogMapping mappingTestLog;
	@Autowired
	private TestSuiteRepository testSuiteRepository;
	@Autowired
	private TestcaseRepository testcaseRepository;
	@Autowired
	private TestLogRepository testLogRepository;
	@Autowired
	private RegresstionRepository regresstionRepository;
	public boolean save(TestSuiteDTO testsuite) {
		boolean result = false;
		try {
			if (testsuite != null) {
				TestSuiteEntity entity = mapping.toEntity(testsuite);
				testSuiteRepository.save(entity);
				result = true;
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean delete(long[] id) {
		boolean result = false;
		try {
			for (long item : id) {
				testSuiteRepository.deleteById(item);
			}
			result = true;
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<TestSuiteDTO> findAlls() {
		List<TestSuiteDTO> testSuiteDTOs = new ArrayList<>();
		try {
			testSuiteDTOs = testSuiteRepository.findAll().stream().sorted(Comparator.comparing(TestSuiteEntity::getRunTime).reversed()).map(i -> mapping.toDTO(i))
					.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testSuiteDTOs;
	}

	public List<TestSuiteDTO> findAlls(Pageable page) {
		List<TestSuiteDTO> testSuiteDTOs = new ArrayList<>();
		try {
			testSuiteDTOs = testSuiteRepository.findAll(page).getContent().stream().map(i -> mapping.toDTO(i))
					.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testSuiteDTOs;
	}

	public boolean update(TestSuiteDTO testsuite) {
		boolean result = false;
		try {
			if (testSuiteRepository.findOneByUUID(testsuite.getUuid())!=null) {
				TestSuiteEntity testSuiteEntity = testSuiteRepository.findOneByUUID(testsuite.getUuid());
				testSuiteEntity.setTestlogSum(testsuite.getTestlogSum());
				testSuiteEntity.setResult(testsuite.getResult());
				testSuiteRepository.saveAndFlush(testSuiteEntity);
				result = true;
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> getCountPassFail() {
		List<Integer> listPassFail = new ArrayList<>();
		try {
			int totalPass = testSuiteRepository.getCountTestSuitePassOrFail(contains.pass);
			int totalFail = testSuiteRepository.getCountTestSuitePassOrFail(contains.fail);
			listPassFail.add(totalPass);
			listPassFail.add(totalFail);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listPassFail;
	}
	/* 	tìm kiếm testsuite có uuid 
		tìm kiếm list<Testcase> theo suiteUUID
	 	với mỗi testcase uuid tìm kiếm testlog
	 */
	@Override
	public testSuiteDetails findOneByUUID(String uuid) {
		testSuiteDetails testSuiteDetails = new testSuiteDetails();
		try {
			TestSuiteEntity testSuiteEntity = testSuiteRepository.findOneByUUID(uuid);
			TestSuiteDTO testestSuiteDTO = mapping.toDTO(testSuiteEntity);
			List<List<TestLogDTO>> testlogList = new ArrayList<>();
			if (testestSuiteDTO!=null) {
				List<TestCaseEntity> testCaseEntities = testcaseRepository.findByTestSuiteUUID(uuid);
				List<TestCaseDTO> testCaseDTOs =  testCaseEntities
													.stream()
													.map(i->mappingTestcase.toDTO(i))
													.collect(Collectors.toList());
				for (TestCaseDTO testCaseDTO : testCaseDTOs) {
					List<TestLogDTO> testLogEntities = testLogRepository.findAllTestWithTestCaseUUID(testCaseDTO.getUuid())
														.stream()
														.map(i->mappingTestLog.toDTO(i))
														.collect(Collectors.toList());
					testlogList.add(testLogEntities);
				}
				testSuiteDetails.setTestSuiteDTO(testestSuiteDTO);
				testSuiteDetails.setTestCaseDTOs(testCaseDTOs);
				testSuiteDetails.setTestLogDTOs(testlogList);
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testSuiteDetails;
	}

	@Override
	public List<TestSuiteDTO> getTestSuiteDTOByDate(String startDate , String endDate) {
		List<TestSuiteDTO> listTestSuiteDTOs = new LinkedList<>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStart = format.parse(startDate);
			Date dateEnd = format.parse(endDate);
			List<TestSuiteEntity> listEntities = testSuiteRepository.getTestSuiteByDateStartAndDateEnd(dateStart,dateEnd);
			listTestSuiteDTOs = listEntities.stream().map(i->mapping.toDTO(i)).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
		return listTestSuiteDTOs;
	}

}
