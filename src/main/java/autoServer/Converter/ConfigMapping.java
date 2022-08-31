package autoServer.Converter;

import autoServer.DTO.ConfigDto;
import autoServer.DTO.TestSuiteDTO;
import autoServer.Entity.ConfigEntity;
import autoServer.Entity.TestSuiteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigMapping {
    @Autowired
    private ModelMapper modelMapper;

    public ConfigDto toDTO(ConfigEntity entity) {
        ConfigDto dto = null;
        try {
            dto = modelMapper.map(entity, ConfigDto.class);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
        return dto;
    }

    public ConfigEntity toEntity(ConfigDto dto) {
        ConfigEntity entity = null;
        try {
            entity = modelMapper.map(dto, ConfigEntity.class);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
        return entity;
    }
}
