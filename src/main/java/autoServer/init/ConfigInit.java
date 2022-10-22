package autoServer.init;

import autoServer.Entity.ConfigEntity;
import autoServer.Utils.contains;
import autoServer.repository.ConfigRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigInit implements CommandLineRunner {
    private ConfigRepository configRepository;
    public ConfigInit(ConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.configRepository.deleteAll();
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setConfigName(contains.IS_REGRESS);
        configEntity.setConfigValue(contains.NO_REGRESS);
        this.configRepository.save(configEntity);
    }
}
