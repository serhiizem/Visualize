package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

@Service
public class XlsService implements Writing {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private Reflections reflections = new Reflections("com.algorithms");
    private HSSFWorkbook workbook = new HSSFWorkbook();

    @Autowired
    public XlsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    @Override
    public void generateStatistics() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException {

        Comparable[] generatedArray = null;

        Random random = new Random(47);

        int arraySize;
        int minValue;
        int maxValue;

        Reflections reflections = new Reflections("com.algorithms");

        int rowCount = 1;
        int columnCount = 1;

        HSSFWorkbook workbook = new HSSFWorkbook();
        for (Class<?> gen : reflections.getSubTypesOf(GenerationStrategy.class)) {

            HSSFSheet sheet = workbook.createSheet(gen.getSimpleName());
            HSSFRow header = sheet.createRow(0);
            for (int j = 1; j < 8; j++) {
                sheet.createRow(j);
            }

            GenerationStrategy gs = (GenerationStrategy) gen.newInstance();
            for (Method generateMethod : getMethods(gen, withAnnotation(Filler.class))) {
                for (int i = 5; i < 30; i += 5) {
                    arraySize = i;
                    minValue = random.nextInt(arraySize);
                    maxValue = random.nextInt(arraySize) + 4 * arraySize;
                    generatedArray = (Comparable[]) generateMethod.invoke(gs, arraySize, minValue, maxValue);
                    for (Class<?> sort : reflections.getSubTypesOf(Sorting.class)) {
                        Comparable[] valuesToSort = generatedArray.clone();
                        Sorting sorting = (Sorting) sort.getConstructor(Queue.class)
                                .newInstance(sortRepresentationQueue);
                        for(Method sortMethod: getMethods(sort, withAnnotation(Sorter.class))) {
                            sortMethod.invoke(sorting, new Object[]{valuesToSort});
                        }
                        SortRepresentation last = sortRepresentationQueue.getLast();
                        Long elapsedTime = last.getElapsedTime();
                        HSSFCell headerCell = sheet.getRow(rowCount).createCell(0);
                        headerCell.setCellValue(sort.getSimpleName());

                        HSSFCell headerRowCell = header.createCell(columnCount);
                        headerRowCell.setCellValue("length:" + i);

                        HSSFCell cell = sheet.getRow(rowCount++).createCell(columnCount);
                        cell.setCellValue(elapsedTime);
                    }
                    columnCount++;
                    rowCount = 1;
                }
            }
            columnCount = 1;
        }

        try (FileOutputStream outputStream = new FileOutputStream("sorts.xlsx")) {
            workbook.write(outputStream);
        }
    }
}
