package com.algorithms.service;

import com.algorithms.entity.Entry;
import com.algorithms.util.Queue;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.algorithms.util.factories.DefaultAlgorithmFactory.NUMBER_OF_ALGORITHMS;

/**
 * This class provides public API that helps to write data into xls files. You can
 * start using this class by instantiating it in a common way:
 * <pre>
 *     XlsService sheetUtil = new XlsService();
 * </pre>
 * A default constructor that is being invoked in this case makes sure that all the class
 * fields are being initialized and are not null. For this reason after creation
 * of any specific {@code XlsService} instance the resulting object will encapsulate a
 * specific "Default" {@link XSSFSheet} object. Default sheet is designed to be replaced by
 * the first custom {@link XSSFSheet}.
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
public class XlsService {

    public static final int HEADER_WIDTH = 1;
    private static final int FIRST_ROW_OF_THE_TABLE = 1;
    private static final int FIRST_COLUMN_OF_THE_TABLE = 1;

    private int rowIndex = FIRST_ROW_OF_THE_TABLE;
    private int columnIndex = FIRST_COLUMN_OF_THE_TABLE;

    public static final String DEFAULT_NAME = "Default";
    //sheet util class works on the same workbook
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public XlsService() {
        this.workbook = new XSSFWorkbook();
        this.sheet = this.workbook.createSheet(DEFAULT_NAME);
    }

    public void createActiveSheet(String sheetName) {
        columnIndex = FIRST_COLUMN_OF_THE_TABLE;
        XSSFSheet defaultSheet = workbook.getSheet(DEFAULT_NAME);
        if(defaultSheet != null) {
            workbook.removeSheetAt(0);
        }
        this.sheet = workbook.createSheet(sheetName);
    }

    public void fillColumn(List<Entry> values, String columnHeader) {
        this.createHeaderForColumn(columnIndex, columnHeader);

        values.forEach(v -> {
            this.createHeaderForRow(rowIndex, v.getName());
            this.writeValueToCell(v.getValue());
            rowIndex++;
        });

        rowIndex = FIRST_ROW_OF_THE_TABLE;
        columnIndex++;
    }

    public void createMultipleRows(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            sheet.createRow(i);
        }
    }

    private void createHeaderForColumn(int columnIndex, String headerName) {
        XSSFRow headerRow = sheet.getRow(0);
        XSSFCell headerRowCell = headerRow.createCell(columnIndex);
        headerRowCell.setCellValue(headerName);
    }

    private void createHeaderForRow(int rowIndex, String headerName) {
        XSSFCell headerCell = sheet.getRow(rowIndex).createCell(0);
        headerCell.setCellValue(headerName);
    }

    private void writeValueToCell(Object value) {
        XSSFCell cell = sheet.getRow(rowIndex).createCell(columnIndex);
        cell.setCellValue((Long) value);
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
        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        XSSFClientAnchor anchor = drawing
                .createAnchor(0, 0, 0, 0, 6, 0, 16, 15);

        Chart lineChart = drawing.createChart(anchor);

        ChartLegend legend = lineChart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        LineChartData data = lineChart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = lineChart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = lineChart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        Queue<ChartDataSource<Number>> numberSourceQueue = new Queue<>();
        int rowIndex = FIRST_ROW_OF_THE_TABLE;

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet,
                new CellRangeAddress(rowIndex, rowIndex++, 1, 4));

        for (int i = 0; i < NUMBER_OF_ALGORITHMS - 1; i++) {
            numberSourceQueue.enqueue(DataSources.fromNumericCellRange(sheet,
                    new CellRangeAddress(rowIndex, rowIndex++, 1, 4)));
        }

        for (ChartDataSource<Number> numberChartDataSource : numberSourceQueue) {
            data.addSeries(xs, numberChartDataSource);
        }

        lineChart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });
    }
}
