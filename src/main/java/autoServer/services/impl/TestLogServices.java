package autoServer.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import autoServer.Converter.TestLogMapping;
import autoServer.DTO.TestLogDTO;
import autoServer.Entity.TestCaseEntity;
import autoServer.Entity.TestLogEntity;
import autoServer.repository.testLogRepository;
import autoServer.services.ITestLogServices;

@Service
public class TestLogServices implements ITestLogServices{

	@Autowired
	private TestLogMapping mapping;
	@Autowired 
	private testLogRepository repository;
	@Override
	public boolean save(TestLogDTO testlog) {
		boolean result = false;
		try {
			TestLogEntity testLog = mapping.toEntity(testlog);
			repository.saveAndFlush(testLog);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean delete(long[] id) {
		boolean result = false;
		try {
			for (long l : id) {
				repository.deleteById(l);
			}
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		return result;
	}

	@Override
	public List<TestLogDTO> findAlls() {
		List<TestLogDTO> testLogDTOs = new ArrayList<TestLogDTO>();
		try {
			testLogDTOs = repository.findAll().stream()
							.map(i -> mapping.toDTO(i))
							.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testLogDTOs;
	}

	@Override
	public List<TestLogDTO> findAlls(Pageable page) {
		List<TestLogDTO> testLogDTOs = new ArrayList<TestLogDTO>();
		try {
			testLogDTOs = repository.findAll().stream()
							.map(i -> mapping.toDTO(i))
							.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testLogDTOs;
	}

	@Override
	public boolean update(TestLogDTO testlog) {
		boolean result = false;
		try {
			TestLogEntity testlogEntity = repository.findById(testlog.getId()).get();
			testlogEntity.setImgPath(testlog.getImgPath());
			testlogEntity.setVideoPath(testlog.getVideoPath());
			testlogEntity.setResult(testlog.getResult());
			repository.saveAndFlush(testlogEntity);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean saveAll(List<TestLogDTO> testLogDTOs) {
		boolean result = false;
		try {
		long number = testLogDTOs.stream().map(i->repository.saveAndFlush(mapping.toEntity(i))).count();
		if ((int)number == testLogDTOs.size()) {
			result = true;
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TestLogDTO> findAllTestLogsWithTestcaseUUID(String uuid) {
		List<TestLogDTO> listTesLog = null;
		try {
			listTesLog = repository.findAllTestWithTestCaseUUID(uuid).stream().map(i->mapping.toDTO(i)).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return listTesLog;
	}

	@Override
	public TestLogDTO findOneByID(Long id) {
		TestLogDTO testLog = null; 
		try {
			testLog = mapping.toDTO(repository.findOneById(id));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testLog;
	}

}

