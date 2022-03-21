package autoServer.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autoServer.DTO.TestSuiteDTO;
import autoServer.Entity.TestSuiteEntity;

@Component
public class TestSuiteMapping {
	@Autowired
	private ModelMapper modelMapper;;

	public TestSuiteDTO toDTO(TestSuiteEntity entity) {
		TestSuiteDTO dto = null;
		try {
			dto = modelMapper.map(entity, TestSuiteDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return dto;
	}

	public TestSuiteEntity toEntity(TestSuiteDTO dto) {
		TestSuiteEntity entity = null;
		try {
			entity = modelMapper.map(dto, TestSuiteEntity.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return entity;
	}
	
}
