package autoServer.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import autoServer.Converter.TestSuiteMapping;
import autoServer.DTO.TestSuiteDTO;
import autoServer.Entity.TestSuiteEntity;
import autoServer.Utils.contains;
import autoServer.repository.testSuiteRepository;
import autoServer.services.ITestSuiteServices;
@Service
public class TestSuiteServices implements ITestSuiteServices{

	@Autowired
	private TestSuiteMapping mapping;
	@Autowired
	private testSuiteRepository testSuiteRepository;
	public boolean save(TestSuiteDTO testsuite) {
		boolean result = false;
		try {
			if(testsuite != null) {
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
		List<TestSuiteDTO> testSuiteDTOs = new ArrayList<TestSuiteDTO>();
		try {
			testSuiteDTOs = testSuiteRepository.findAll().stream()
							.map(i -> mapping.toDTO(i))
							.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testSuiteDTOs;
	}

	public List<TestSuiteDTO> findAlls(Pageable page) {
		List<TestSuiteDTO> testSuiteDTOs = new ArrayList<TestSuiteDTO>();
		try {
			testSuiteDTOs = testSuiteRepository.findAll(page).getContent().stream()
							.map(i -> mapping.toDTO(i))
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
			TestSuiteEntity testSuiteEntity = testSuiteRepository.findById(testsuite.getId()).get();
			testSuiteEntity.setMethodPass(testsuite.getMethodPass());
			testSuiteEntity.setMethodFail(testsuite.getMethodFail());
			testSuiteEntity.setResult(testsuite.getResult());
			testSuiteRepository.saveAndFlush(testSuiteEntity);
			result = true;
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> getCountPassFail() {
		List<Integer> listPassFail = new ArrayList<Integer>();
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

	@Override
	public TestSuiteDTO findOneByUUID(String uuid) {
		TestSuiteDTO testSuiteDTO = null;
		try {
			testSuiteDTO = mapping.toDTO(testSuiteRepository.findOneByUUID(uuid));
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testSuiteDTO;
	}

}
