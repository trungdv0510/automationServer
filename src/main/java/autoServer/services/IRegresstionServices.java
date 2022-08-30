package autoServer.services;

import autoServer.DTO.RegresstionDto;
import autoServer.DTO.TestSuiteDTO;

import java.util.Date;
import java.util.List;

public interface IRegresstionServices {
    public List<RegresstionDto> getListRegresstionDto(Date startDate, Date endDate, Integer sprint);
    public void save(RegresstionDto regresstionDto);
}
