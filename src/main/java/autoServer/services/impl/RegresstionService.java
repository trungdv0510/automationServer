package autoServer.services.impl;

import autoServer.Converter.RegresstionMapping;
import autoServer.DTO.RegresstionDto;
import autoServer.Entity.RegresstionEntity;
import autoServer.Utils.contains;
import autoServer.repository.RegresstionInteface;
import autoServer.repository.RegresstionRepository;
import autoServer.services.FileExcel;
import autoServer.services.IConfigService;
import autoServer.services.IRegresstionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegresstionService implements IRegresstionServices {
    @Autowired
    private RegresstionInteface regresstionInteface;
    @Autowired
    private RegresstionRepository repository;
    @Autowired
    private RegresstionMapping regresstionMapping;
    @Autowired
    private IConfigService configService;
    @Autowired
    private FileExcel fileExcel;
    @Override
    public List<RegresstionDto> getListRegresstionDto(Date startDate, Date endDate, String sprint){
        return regresstionInteface.getListRegresstionTest(startDate,endDate,sprint);
    }
    @Override
    public void save(RegresstionDto regresstionDto){
        String configRegress = configService.getValueConfig(contains.IS_REGRESS);
        if (configRegress.equalsIgnoreCase(contains.YES_REGRESS)){
            RegresstionEntity regresstionEntity = regresstionMapping.toEntity(regresstionDto);
            repository.save(regresstionEntity);
        }
    }

    @Override
    public List<String> getSprint() {
        return regresstionInteface.getSprint();
    }

    @Override
    public byte[] getReportAutomation(Date startDate, Date endDate, String sprint) throws IOException {
        List<RegresstionDto> regressionDtoList = regresstionInteface.getListRegresstionTest(startDate,endDate,sprint);
        byte[] fileReport = fileExcel.renderReport(regressionDtoList);
        return fileReport;
    }

    @Override
    public Map<String, Integer> getSprintAndTotal() {
        Map<String,Integer> sprintAndTotal = new HashMap<>();
        List<String> sprints = regresstionInteface.getSprint();
        for(String sprint : sprints){
            Integer total = regresstionInteface.getTotalWithSprint(sprint,"PASS");
            sprintAndTotal.put(sprint,total);
        }
        return sprintAndTotal;
    }
}
