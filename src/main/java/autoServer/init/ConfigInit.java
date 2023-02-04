package autoServer.init;

import autoServer.Entity.ConfigEntity;
import autoServer.Utils.contains;
import autoServer.repository.ConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfigInit implements CommandLineRunner {
    private ConfigRepository configRepository;
    public ConfigInit(ConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       //this.configRepository.deleteAll();
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setConfigName(contains.IS_REGRESS);
        configEntity.setConfigValue(contains.NO_REGRESS);
        log.info("Create data config");
        //this.configRepository.save(configEntity);
    }
}
