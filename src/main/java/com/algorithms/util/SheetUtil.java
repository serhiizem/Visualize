package com.algorithms.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SheetUtil {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;

    public SheetUtil(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void createSheet(String sheetName) {
        this.sheet = workbook.createSheet(sheetName);
    }

    public void createMultipleRows(int numberOfRows) {
        for (int j = 1; j < numberOfRows + 1; j++) {
            sheet.createRow(j);
        }
    }

    public void createHeaderForCloumn(int columnIndex, Object headerName) {
        HSSFRow header = sheet.createRow(0);
        HSSFCell headerRowCell = header.createCell(columnIndex);
        headerRowCell.setCellValue("length:" + headerName);
    }

    public void createHeaderForRow(int rowIndex, String headerName) {
        HSSFCell headerCell = sheet.getRow(rowIndex).createCell(0);
        headerCell.setCellValue(headerName);
    }

    public void writeValueToCell(int cellRow, int cellColumn, Long value) {
        HSSFCell cell = sheet.getRow(cellRow++).createCell(cellColumn);
        cell.setCellValue(value);
    }
}
