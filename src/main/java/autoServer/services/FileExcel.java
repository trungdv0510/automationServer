package autoServer.services;

import autoServer.DTO.RegresstionDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface FileExcel {
    Resource getFileReport(List<RegresstionDto> regressionDto) throws IOException;

    byte[] renderReport(List<RegresstionDto> regressionDto) throws IOException;


    byte[] inputReportExcelFile(Resource resource,List<RegresstionDto> regressionDto) throws IOException;
}
