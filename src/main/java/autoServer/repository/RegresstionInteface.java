package autoServer.repository;

import autoServer.DTO.RegresstionDto;
import autoServer.Entity.RegresstionEntity;

import java.util.Date;
import java.util.List;

public interface RegresstionInteface {
    public List<RegresstionDto> getListRegresstionTest(Date startDate, Date endDate, Integer sprint);
}
