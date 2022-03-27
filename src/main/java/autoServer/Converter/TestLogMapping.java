package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.TestLogDTO;
import autoServer.Entity.TestCaseEntity;
import autoServer.Entity.TestLogEntity;
import autoServer.repository.testcaseRepository;

@Component
public class TestLogMapping{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private testcaseRepository testcase;
	public TestLogDTO toDTO(TestLogEntity entity) {
		TestLogDTO testLog = null;
		try {
			testLog = modelMapper.map(entity,TestLogDTO.class);
			testLog.setTestcaseUUID(entity.getTestcaseuuid().getUuid());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return testLog;
	}

	public TestLogEntity toEntity(TestLogDTO dto) {
		TestLogEntity testLog = null;
		try {
			TestCaseEntity testCaseEntity = testcase.findByUUID(dto.getTestcaseUUID());
			testLog = modelMapper.map(dto,TestLogEntity.class);
			testLog.setTestcaseuuid(testCaseEntity);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return testLog;
	}

}
