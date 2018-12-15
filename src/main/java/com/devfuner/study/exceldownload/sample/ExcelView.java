package com.devfuner.study.exceldownload.sample;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("excelView")
public class ExcelView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        List<String> header = (List<String>) model.get("header");
        String filename = (String) model.get("downloadFileName");
        List<SampleData> list = (List<SampleData>) model.get("list");

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        Sheet sheet = workbook.createSheet("Sheet1");

        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));

        int headerCount = 1;
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < header.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header.get(i));
        }

        for (int i = 0 ; i < list.size() ; i++) {
            SampleData stat = list.get(i);
            Row row = sheet.createRow(i + headerCount);

            Cell cell0 = row.createCell(0);
            cell0.setCellValue(stat.getName());

            Cell cell1 = row.createCell(1);
            cell1.setCellType(CellType.NUMERIC);
            cell1.setCellValue(stat.getValue1());
            cell1.setCellStyle(numberCellStyle);

            Cell cell2 = row.createCell(2);
            cell2.setCellType(CellType.NUMERIC);
            cell2.setCellValue(stat.getValue2());
            cell2.setCellStyle(numberCellStyle);
        }
    }
}