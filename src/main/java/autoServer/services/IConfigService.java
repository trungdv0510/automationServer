package autoServer.services;

import autoServer.DTO.ConfigDto;

public interface IConfigService {
    public String getValueConfig(String ConfigName);
    public void updateConfig(ConfigDto configDto);
}
