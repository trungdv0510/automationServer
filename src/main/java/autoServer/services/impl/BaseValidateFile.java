package autoServer.services.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class BaseValidateFile {

    public void createCell(Workbook workbook, Sheet sheet, Row row, String cellValue, int index) {
        Font fNotBold = sheet.getWorkbook().createFont();
        fNotBold.setFontName("Arial");
        fNotBold.setFontHeightInPoints((short) 11);

        fNotBold.setBold(false);

        final CellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setWrapText(true);
        cellStyleCommon.setFont(fNotBold);
        cellStyleCommon.setBorderTop(BorderStyle.THIN);
        cellStyleCommon.setBorderBottom(BorderStyle.THIN);
        cellStyleCommon.setBorderLeft(BorderStyle.THIN);
        cellStyleCommon.setBorderRight(BorderStyle.THIN);

        Cell tempCell = row.createCell(index);
        tempCell.setCellValue(cellValue);
        tempCell.setCellStyle(cellStyleCommon);
    }
    public void createCellPercent(Workbook workbook, Sheet sheet, Row row, double cellValue, int index) {
        Font fNotBold = sheet.getWorkbook().createFont();
        fNotBold.setFontName("Arial");
        fNotBold.setFontHeightInPoints((short) 11);

        fNotBold.setBold(false);

        final CellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setWrapText(true);
        cellStyleCommon.setFont(fNotBold);
        cellStyleCommon.setBorderTop(BorderStyle.THIN);
        cellStyleCommon.setBorderBottom(BorderStyle.THIN);
        cellStyleCommon.setBorderLeft(BorderStyle.THIN);
        cellStyleCommon.setBorderRight(BorderStyle.THIN);
        cellStyleCommon.setDataFormat(workbook.createDataFormat().getFormat("0.000%"));
        Cell tempCell = row.createCell(index);
        tempCell.setCellValue(cellValue);
        tempCell.setCellStyle(cellStyleCommon);
    }


    public ByteArrayResource getByteArrayResource(XSSFWorkbook wb) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            wb.write(byteArrayOutputStream);
        } finally {
            byteArrayOutputStream.close();
        }
        return new ByteArrayResource(byteArrayOutputStream.toByteArray());
    }

    public CellStyle getCellStyle(XSSFWorkbook wb, XSSFSheet sheet1) {
        final CellStyle cellStyleCommon = wb.createCellStyle();
        cellStyleCommon.setWrapText(true);
        cellStyleCommon.setBorderTop(BorderStyle.THIN);
        cellStyleCommon.setBorderBottom(BorderStyle.THIN);
        cellStyleCommon.setBorderLeft(BorderStyle.THIN);
        cellStyleCommon.setBorderRight(BorderStyle.THIN);
        Font f = sheet1.getWorkbook().createFont();
        f.setFontName("Arial");
        f.setFontHeightInPoints((short) 11);
        f.setBold(true);
        cellStyleCommon.setFont(f);
        return cellStyleCommon;
    }

    public void writeFileToTest(Workbook wb) throws Exception{
        FileOutputStream fos = new FileOutputStream("workbook.xlsx");
        wb.write(fos);
        fos.close();
    }
}
