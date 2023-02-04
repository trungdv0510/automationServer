package autoServer.services.impl;

import autoServer.Converter.TestLogMapping;
import autoServer.DTO.TestLogDTO;
import autoServer.Entity.TestLogEntity;
import autoServer.Utils.contains;
import autoServer.Utils.fileUtils;
import autoServer.Utils.minioUtils;
import autoServer.repository.TestLogRepository;
import autoServer.services.ITestLogServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Log4j2
@Service
public class TestLogServices implements ITestLogServices {

	@Autowired
	private TestLogMapping mapping;
	@Autowired
	private TestLogRepository repository;
	@Autowired
	private minioUtils minio;
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
		List<TestLogDTO> testLogDTOs = new ArrayList<>();
		try {
			testLogDTOs = repository.findAll().stream().map(i -> mapping.toDTO(i)).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testLogDTOs;
	}

	@Override
	public List<TestLogDTO> findAlls(Pageable page) {
		List<TestLogDTO> testLogDTOs = new ArrayList<>();
		try {
			testLogDTOs = repository.findAll().stream().map(i -> mapping.toDTO(i)).collect(Collectors.toList());
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
			if (repository.findOneByUuid(testlog.getUuid())!=null){
				TestLogEntity testlogEntity = repository.findOneByUuid(testlog.getUuid());
				testlogEntity.setStepName(testlog.getStepName());
				testlogEntity.setResult(testlog.getResult());
				repository.saveAndFlush(testlogEntity);
				result = true;
			}
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
			int count = 0;
			for (TestLogDTO testLogDTO : testLogDTOs) {
				repository.saveAndFlush(mapping.toEntity(testLogDTO));
				count++;
			}
			if (count == testLogDTOs.size()) {
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
		List<TestLogDTO> listTesLog = new ArrayList<>();
		try {
			if (!repository.findAllTestWithTestCaseUUID(uuid).isEmpty()){
				listTesLog = repository.findAllTestWithTestCaseUUID(uuid).stream().map(i -> mapping.toDTO(i))
						.collect(Collectors.toList());
				listTesLog.forEach(testLogDTO -> {
					String fileName = testLogDTO.getStepName();
					testLogDTO.setStepName(minio.getFileUrl(fileName));
				});
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return listTesLog;
	}

	@Override
	public TestLogDTO findOneByID(String id) {
		TestLogDTO testLog = null;
		try {
			testLog = mapping.toDTO(repository.findOneByUuid(id));
			String fileName = testLog.getStepName();
			testLog.setStepName(minio.getFileUrl(fileName));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testLog;
	}

	@Override
	public String saveImgOrVideo(MultipartFile file) {
		String fileName = "Error";
		try {
			fileName = LocalDateTime.now() + file.getOriginalFilename();
			minio.uploadFile(fileName,file);
			return fileName;
		} catch (Exception e) {
			// TODO: handle exceptionTC
			System.err.println(">>>>>>>>>>>>>>>>>>>>lỗi nè "+e.getMessage());
		}
		return null;
	}
}
