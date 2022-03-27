package autoServer.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import autoServer.DTO.TestLogDTO;
import autoServer.DTO.TestSuiteDTO;

public interface ITestLogServices {
	public boolean save(TestLogDTO testlog);
	public boolean delete(long[] id);
	public List<TestLogDTO> findAlls();
	public List<TestLogDTO> findAlls(Pageable page);
	public boolean update(TestLogDTO testlog);
	public boolean saveAll(List<TestLogDTO> testLogDTOs);
	public List<TestLogDTO> findAllTestLogsWithTestcaseUUID(String uuid);
	public TestLogDTO findOneByID(Long id);
	public String saveImgOrVideo(MultipartFile imgFile);
}
