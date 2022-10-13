package autoServer.services;

import autoServer.DTO.RegresstionDto;
import autoServer.DTO.TestSuiteDTO;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRegresstionServices {
    List<RegresstionDto> getListRegresstionDto(Date startDate, Date endDate, String sprint);
    void save(RegresstionDto regresstionDto);
    List<String> getSprint();

    byte[] getReportAutomation(Date startDate, Date endDate, String sprint ) throws IOException;

    Map<String,Integer> getSprintAndTotal();
}
