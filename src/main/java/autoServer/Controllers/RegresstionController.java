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
@RequestMapping("/regresstion")
public class RegresstionController {
    @Autowired
    private IRegresstionServices regresstionServices;
    @GetMapping("/search")
    public List<RegresstionDto> getRegresstionTest(@RequestParam(value = "startDate",required = false) Date startDate,
                                                   @RequestParam(value = "endDate",required = false) Date endDate,
                                                   @RequestParam(value="sprint",required = false) int sprint){
        return regresstionServices.getListRegresstionDto(startDate,endDate,sprint);
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveRegresstionTest(@RequestBody RegresstionDto regresstionDto){
        try{
            regresstionServices.save(regresstionDto);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
