package autoServer.services.impl;

import autoServer.DTO.RegresstionDto;
import autoServer.Utils.FunctionUtils;
import autoServer.Utils.contains;
import autoServer.repository.RegresstionInteface;
import autoServer.services.FileExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static autoServer.Utils.contains.AUTOMATION_REPORT_FILE_NAME;

@Slf4j
@Service
public class FileExcelImpl implements FileExcel {

    @Autowired
    ResourcePatternResolver resourcePatternResolver;
    @Autowired
    private RegresstionInteface repository;

    @Autowired
    private BaseValidateFile baseValidateFile;
    @Override
    public Resource getFileReport( List<RegresstionDto> regressionDtoList) throws IOException {
        Resource[] resources = resourcePatternResolver.getResources(contains.CLASSPATH + "*");
        Optional<Resource> resourceOptional = Arrays.stream(resources).filter(resource -> AUTOMATION_REPORT_FILE_NAME.equalsIgnoreCase(resource.getFilename())).findFirst();
        return  resourceOptional.orElseThrow(() -> new IllegalArgumentException("Cannot found file excel to render report"));
    }

    @Override
    public byte[] renderReport( List<RegresstionDto> regressionDtoList) throws IOException {
        Resource resourceFile = getFileReport(regressionDtoList);
        try (InputStream inputStream = resourceFile.getInputStream()) {
            byte[] bytesFile = IOUtils.toByteArray(inputStream);
            if (Objects.requireNonNull(resourceFile.getFilename()).contains(AUTOMATION_REPORT_FILE_NAME)) {
                bytesFile = inputReportExcelFile(resourceFile,regressionDtoList);
            }
            return bytesFile;
        }
    }

    @Override
    public byte[] inputReportExcelFile(Resource resource, List<RegresstionDto> regressionDtoList) throws IOException {
        Set<String> listSprint = new HashSet<>();
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        if (regressionDtoList != null){
            int size = regressionDtoList.size();
            Sheet sheet = workbook.getSheet("Report");
            for (int i = 0; i < size; i++) {
                RegresstionDto regresstionDto = regressionDtoList.get(i);
                listSprint.add(regresstionDto.getSprint());
                Row tempRow = sheet.createRow(i + 1);
                baseValidateFile.createCell(workbook, sheet, tempRow, String.valueOf(i + 1), 0);
                baseValidateFile.createCell(workbook, sheet, tempRow,regresstionDto.getTestcaseName(), 1);
                baseValidateFile.createCell(workbook, sheet, tempRow, regresstionDto.getEvidenceLink(), 2);
                baseValidateFile.createCell(workbook, sheet, tempRow, regresstionDto.getSprint(), 3);
                baseValidateFile.createCell(workbook, sheet, tempRow,regresstionDto.getAuthor(), 4);
                baseValidateFile.createCell(workbook, sheet, tempRow, FunctionUtils.parseToString(regresstionDto.getDateRun()), 5);
                baseValidateFile.createCell(workbook, sheet, tempRow, regresstionDto.getResult(), 6);
                baseValidateFile.createCell(workbook, sheet, tempRow,regresstionDto.getErrorDescription(), 7);
            }
        }
        Sheet sheetReport = workbook.getSheet("Dashboard");
        double totalTestCase = regressionDtoList.size();
        double totalPass = regressionDtoList.stream().filter(i->i.getResult().equalsIgnoreCase("PASS")).count();
        double totalFail = regressionDtoList.stream().filter(i->i.getResult().equalsIgnoreCase("FAIL")).count();
        sheetReport.getRow(3).getCell(1).setCellValue(totalTestCase);
        sheetReport.getRow(3).getCell(2).setCellValue(totalPass);
        sheetReport.getRow(3).getCell(3).setCellValue(totalFail);
        double passPercentTotal = totalPass/totalTestCase;
        double failPercentTotal = totalFail/totalTestCase;
        sheetReport.getRow(3).getCell(4).setCellValue(passPercentTotal);
        sheetReport.getRow(3).getCell(5).setCellValue(failPercentTotal);
        if (listSprint!=null){
            int i = 0;
            for (String sprint: listSprint){
               List<RegresstionDto> listSprintItem = regressionDtoList.stream().filter(item->item.getSprint().equalsIgnoreCase(sprint)).collect(Collectors.toList());
                double totalPassSprint =  listSprintItem.stream().filter(item->item.getResult().equalsIgnoreCase("PASS")).count();
                double totalFailSprint =  listSprintItem.stream().filter(item->item.getResult().equalsIgnoreCase("FAIL")).count();
                double totalSprint = listSprintItem.size();
                double passPercent = (totalPassSprint/totalSprint) ;
                double failPercent = (totalFailSprint/totalSprint);
                int startRow = i+25;
                Row tempRow = sheetReport.createRow(startRow);
                baseValidateFile.createCell(workbook,sheetReport,tempRow,sprint,1);
                baseValidateFile.createCell(workbook,sheetReport,tempRow,String.valueOf(totalPassSprint),2);
                baseValidateFile.createCell(workbook,sheetReport,tempRow,String.valueOf(totalFailSprint),3);
                baseValidateFile.createCell(workbook,sheetReport,tempRow,String.valueOf(totalSprint),4);
                baseValidateFile.createCellPercent(workbook,sheetReport,tempRow,passPercent,5);
                baseValidateFile.createCellPercent(workbook,sheetReport,tempRow,failPercent,6);
                i++;
            }
        }
        ByteArrayOutputStream ms = new ByteArrayOutputStream();
        try {
            workbook.write(ms);
            ms.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ms.toByteArray();
    }
}
