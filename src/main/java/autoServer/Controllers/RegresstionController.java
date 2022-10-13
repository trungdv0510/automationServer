package autoServer.Controllers;

import autoServer.DTO.RegresstionDto;
import autoServer.Utils.FunctionUtils;
import autoServer.services.IRegresstionServices;
import autoServer.services.impl.RegresstionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static autoServer.Utils.contains.AUTOMATION_REPORT_FILE_NAME;

@RestController
@RequestMapping("/api/regression")
public class RegresstionController {
    @Autowired
    private IRegresstionServices regressionServices;
    @GetMapping("/search")
    public List<RegresstionDto> getRegressionTest(@RequestParam(value = "startDate",required = false) String startDate,
                                                   @RequestParam(value = "endDate",required = false) String endDate,
                                                   @RequestParam(value="sprint",required = false) String sprint) throws ParseException {
        Date startTime = null;
        Date endTime = null;
        if (!FunctionUtils.isNullOrEmpty(startDate)){
            startTime = FunctionUtils.convertStringToDate(startDate);
       }
       if(!FunctionUtils.isNullOrEmpty(endDate)){
           endTime = FunctionUtils.convertStringToDate(endDate);
       }
        return regressionServices.getListRegresstionDto(startTime,endTime,sprint);
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
    @GetMapping(value = "/report-automation")
    public ResponseEntity<?> reportAutomation(@RequestParam("startTime") String startTime,
                                              @RequestParam("endTime") String endTime,
                                              @RequestParam("sprint") String sprint) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("File", AUTOMATION_REPORT_FILE_NAME);
            headers.set("Content-Disposition", "attachment; filename=" + AUTOMATION_REPORT_FILE_NAME);
            headers.set("Access-Control-Expose-Headers", "File");
            Date startDate = null;
            Date endDate = null;
            if (!FunctionUtils.isNullOrEmpty(startTime)){
                startDate = FunctionUtils.convertStringToDate(startTime);
            }
            if(!FunctionUtils.isNullOrEmpty(endTime)){
                endDate = FunctionUtils.convertStringToDate(endTime);
            }
            byte[] bytesFile = regressionServices.getReportAutomation(startDate,endDate,sprint);
            return new ResponseEntity<>(bytesFile, headers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sprint-total")
    public ResponseEntity<?> getSprintAndTotal(){
        try{
            Map<String,Integer> sprintAndTotal = regressionServices.getSprintAndTotal();
            return new ResponseEntity<>(sprintAndTotal,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
