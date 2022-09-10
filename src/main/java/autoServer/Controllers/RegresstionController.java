package autoServer.Controllers;

import autoServer.DTO.RegresstionDto;
import autoServer.services.IRegresstionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/regression")
public class RegresstionController {
    @Autowired
    private IRegresstionServices regressionServices;
    @GetMapping("/search")
    public List<RegresstionDto> getRegressionTest(@RequestParam(value = "startDate",required = false) Date startDate,
                                                   @RequestParam(value = "endDate",required = false) Date endDate,
                                                   @RequestParam(value="sprint",required = false) int sprint){
        return regressionServices.getListRegresstionDto(startDate,endDate,sprint);
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveRegressionTest(@RequestBody RegresstionDto regresstionDto){
        try{
            regressionServices.save(regresstionDto);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/sprint")
    public ResponseEntity<?> getSprint(){
        try{
            List<String> listSprint =  regressionServices.getSprint();
            return new ResponseEntity<>(listSprint,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
