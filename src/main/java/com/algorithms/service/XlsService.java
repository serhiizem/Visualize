package com.algorithms.service;

import com.algorithms.sorts.Sorting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.algorithms.util.WorkbookSingleton.getWorkbookSingleton;

/**
 * @author Zemlianiy
 * @version 1.0
 */
public class XlsService {


    private XSSFSheet sheet;
    //sheet util class works on the same workbook
    private XSSFWorkbook workbook;

    public XlsService(String sheetName) {
        this.workbook = getWorkbookSingleton();
        this.sheet = workbook.createSheet(sheetName);
    }

    public void createMultipleRows(int numberOfRows) {
        for (int j = 0; j < numberOfRows + 1; j++) {
            sheet.createRow(j);
        }
    }



    public void createHeaderForColumn(int columnIndex, Object headerName) {
        XSSFRow headerRow = sheet.getRow(0);
        XSSFCell headerRowCell = headerRow.createCell(columnIndex);
        headerRowCell.setCellValue("length:" + headerName);
    }

    public void createHeaderForRow(int rowIndex, String headerName) {
        XSSFCell headerCell = sheet.getRow(rowIndex).createCell(0);
        headerCell.setCellValue(headerName);
    }

    public void writeValueToCell(int cellRow, int cellColumn, Long value) {
        XSSFCell cell = sheet.getRow(cellRow).createCell(cellColumn);
        cell.setCellValue(value);
    }

    public void writeToFile(String fileName) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createChart() {
        XSSFDrawing xlsx_drawing = sheet.createDrawingPatriarch();

        XSSFClientAnchor anchor = xlsx_drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

        Chart my_line_chart = xlsx_drawing.createChart(anchor);

        ChartLegend legend = my_line_chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 1, 5));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 1, 5));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(3, 3, 1, 5));
        ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 1, 5));
        ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 1, 5));
        ChartDataSource<Number> ys5 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(6, 6, 1, 5));
        ChartDataSource<Number> ys6 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(7, 7, 1, 5));

        data.addSeries(xs, ys1);
        data.addSeries(xs, ys2);
        data.addSeries(xs, ys3);
        data.addSeries(xs, ys4);
        data.addSeries(xs, ys5);
        data.addSeries(xs, ys6);

        my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });
    }

    public void writeEntry(String entryName, Long entryValue) {

    }
}
