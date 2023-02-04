package autoServer.services.impl;

import autoServer.Converter.TestLogMapping;
import autoServer.DTO.TestLogDTO;
import autoServer.Entity.TestLogEntity;
import autoServer.Utils.contains;
import autoServer.Utils.fileUtils;
import autoServer.repository.TestLogRepository;
import autoServer.services.ITestLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TestLogServices implements ITestLogServices {

	@Autowired
	private TestLogMapping mapping;
	@Autowired
	private TestLogRepository repository;

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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testLog;
	}

	@Override
	public String saveImgOrVideo(MultipartFile file) {
		String filePath = "Error";
		try {
			String currentDirectory = System.getProperty("user.dir");
			System.out.println("user.dir: " + currentDirectory);
			System.out.println("Đã chạy vào đây ");
			if (fileUtils.checkContenTypeFile(file)) {
				int count = 0;
				String fileName = contains.randomDate() + file.getOriginalFilename();
				if(Objects.equals(file.getContentType(), contains.contenTypeImg)) {
					System.out.println("Đã chạy vào đây check images");
					filePath = contains.folderPublic + contains.folderImg + fileName;
					 count++;
				}
				else if(Objects.equals(file.getContentType(), contains.contentTypeVideo)) {
					System.out.println("Đã chạy vào đây check video");
					filePath = contains.folderPublic + contains.folderVideo + fileName;
					 count++;
				}
				if (count>=1) {
					System.out.println("Bắt đầu lưu file ");
					File convertFile = new File(filePath);
					convertFile.createNewFile();
					FileOutputStream fout = new FileOutputStream(convertFile);
					fout.write(file.getBytes());
					fout.close();
					System.out.println("Lưu file thành công ");
				}
			}
		} catch (Exception e) {
			// TODO: handle exceptionTC
			System.err.println(">>>>>>>>>>>>>>>>>>>>lỗi nè "+e.getMessage());
		}
		System.out.println(filePath);
		return filePath;
	}
}
