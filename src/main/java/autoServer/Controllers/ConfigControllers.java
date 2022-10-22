package autoServer.Controllers;

import autoServer.DTO.ConfigDto;
import autoServer.services.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class ConfigControllers {
    @Autowired
    private IConfigService configService;
    @PostMapping("config/update")
    public ResponseEntity<?> updateConfig(@Valid @RequestBody ConfigDto config){
        try{
            configService.updateConfig(config);
            return new ResponseEntity<>(config.getConfigValue(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/config")
    public ResponseEntity<?> getValueConfigWithName(@RequestParam("configName") String configName){
        try{
            String valueConfig = configService.getValueConfig(configName);
            return new ResponseEntity<>(valueConfig,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
