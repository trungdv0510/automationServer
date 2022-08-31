package autoServer.Controllers;

import autoServer.DTO.ConfigDto;
import autoServer.services.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/config")
public class ConfigControllers {
    @Autowired
    private IConfigService configService;
    @PostMapping
    public ResponseEntity<?> configValueWithName(@Valid @RequestBody ConfigDto config){
        try{
            configService.updateConfig(config);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
