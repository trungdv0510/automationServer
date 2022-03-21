package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.TestLogDTO;
import autoServer.Entity.TestLogEntity;

@Component
public class TestLogMapping{
	@Autowired
	private ModelMapper modelMapper;

	public TestLogDTO toDTO(TestLogEntity entity) {
		TestLogDTO testLog = null;
		try {
			testLog = modelMapper.map(entity,TestLogDTO.class);
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
			testLog = modelMapper.map(dto,TestLogEntity.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return testLog;
	}

}
