package com.algorithms.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.algorithms.util.WorkbookSingleton.getWorkbookSingleton;

/**
 * @author Zemlianiy
 * @version 1.0
 */
public class SheetUtil {

    private HSSFSheet sheet;
    //sheet util class works on the same workbook
    private HSSFWorkbook workbook = getWorkbookSingleton();

    public SheetUtil(String sheetName) {
        this.workbook = getWorkbookSingleton();
        this.sheet = workbook.createSheet(sheetName);
    }

    public void createMultipleRows(int numberOfRows) {
        for (int j = 0; j < numberOfRows + 1; j++) {
            sheet.createRow(j);
        }
    }

    public void createHeaderForColumn(int columnIndex, Object headerName) {
        HSSFRow headerRow = sheet.getRow(0);
        HSSFCell headerRowCell = headerRow.createCell(columnIndex);
        headerRowCell.setCellValue("length:" + headerName);
    }

    public void createHeaderForRow(int rowIndex, String headerName) {
        HSSFCell headerCell = sheet.getRow(rowIndex).createCell(0);
        headerCell.setCellValue(headerName);
    }

    public void writeValueToCell(int cellRow, int cellColumn, Long value) {
        HSSFCell cell = sheet.getRow(cellRow).createCell(cellColumn);
        cell.setCellValue(value);
    }

    public void writeToFile(String fileName) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
