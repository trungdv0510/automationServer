package autoServer.services;

import autoServer.DTO.RegresstionDto;
import autoServer.DTO.TestSuiteDTO;

import java.util.Date;
import java.util.List;

public interface IRegresstionServices {
    List<RegresstionDto> getListRegresstionDto(Date startDate, Date endDate, Integer sprint);
    void save(RegresstionDto regresstionDto);
    List<String> getSprint();
}
