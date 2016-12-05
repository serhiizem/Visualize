package com.algorithms.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookSingleton {

    private static XSSFWorkbook workbook;

    private WorkbookSingleton() {}

    public static XSSFWorkbook getWorkbookSingleton() {
        if (workbook == null) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }
}
