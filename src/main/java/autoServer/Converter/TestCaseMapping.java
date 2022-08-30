package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.TestCaseDTO;
import autoServer.Entity.TestCaseEntity;
import autoServer.Entity.TestSuiteEntity;
import autoServer.repository.TestSuiteRepository;

@Component
public class TestCaseMapping{
	@Autowired
	private ModelMapper modelmaper;
	@Autowired 
	private TestSuiteRepository testsuite;
	public TestCaseDTO toDTO(TestCaseEntity dto) {
		TestCaseDTO testCaseDTO = null;
		try {
			testCaseDTO = modelmaper.map(dto, TestCaseDTO.class);
			testCaseDTO.setSuiteUUID(dto.getSuiteuuid().getUuid());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testCaseDTO;
	}
	
	public TestCaseEntity toEntity(TestCaseDTO entity) {
		TestCaseEntity tesCaseEntity = null;
		try {
			TestSuiteEntity testSuiteEntity = testsuite.findOneByUUID(entity.getSuiteUUID());
			tesCaseEntity = modelmaper.map(entity, TestCaseEntity.class);
			tesCaseEntity.setSuiteuuid(testSuiteEntity);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return tesCaseEntity;
	}
}
