package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import autoServer.DTO.TestLogDTO;
import autoServer.DTO.TestSuiteDTO;

public interface ITestLogServices {
	boolean save(TestLogDTO testlog);
	boolean delete(long[] id);
	List<TestLogDTO> findAlls();
	List<TestLogDTO> findAlls(Pageable page);
	boolean update(TestLogDTO testlog);
	boolean saveAll(List<TestLogDTO> testLogDTOs);
	List<TestLogDTO> findAllTestLogsWithTestcaseUUID(String uuid);
	TestLogDTO findOneByID(Long id);
	String saveImgOrVideo(MultipartFile imgFile);
}
