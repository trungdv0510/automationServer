package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.TestCaseDTO;
import autoServer.Entity.TestCaseEntity;

@Component
public class TestCaseMapping{
	@Autowired
	private ModelMapper modelmaper;
	
	public TestCaseDTO toDTO(TestCaseEntity dto) {
		TestCaseDTO testCaseDTO = null;
		try {
			testCaseDTO = modelmaper.map(dto, TestCaseDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testCaseDTO;
	}
	
	public TestCaseEntity toEntity(TestCaseDTO entity) {
		TestCaseEntity tesCaseEntity = null;
		try {
			tesCaseEntity = modelmaper.map(entity, TestCaseEntity.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return tesCaseEntity;
	}
}
