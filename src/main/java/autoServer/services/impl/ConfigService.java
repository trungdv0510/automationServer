package autoServer.services.impl;

import autoServer.Converter.ConfigMapping;
import autoServer.DTO.ConfigDto;
import autoServer.Entity.ConfigEntity;
import autoServer.repository.ConfigRepository;
import autoServer.services.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService implements IConfigService {

    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private ConfigMapping configMapping;
    @Override
    public String getValueConfig(String configName) {
        return configRepository.getConfigValue(configName);
    }

    @Override
    public void updateConfig(ConfigDto configDto) {
        ConfigEntity configEntity = configMapping.toEntity(configDto);
        configRepository.save(configEntity);
    }
}
