package autoServer.repository;

import autoServer.DTO.RegresstionDto;

import java.util.Date;
import java.util.List;

public interface RegresstionInteface {
    List<RegresstionDto> getListRegresstionTest(Date startDate, Date endDate, String sprint);

    List<String> getSprint();
    Integer getTotalWithSprint(String sprintName, String result);

}
