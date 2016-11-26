package com.algorithms.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WorkbookSingleton {

    private static HSSFWorkbook workbook;

    private WorkbookSingleton() {}

    public static HSSFWorkbook getWorkbookSingleton() {
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
        return workbook;
    }
}
