package autoServer.services;

import autoServer.DTO.ConfigDto;

public interface IConfigService {
     String getValueConfig(String ConfigName);
     void updateConfig(ConfigDto configDto);
}
