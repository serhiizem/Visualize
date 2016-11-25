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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
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
    public void generateStatistics() {

        Comparable[] generatedArray = null;

        Random random = new Random(47);

        int arraySize;
        int minValue;
        int maxValue;

        Reflections reflections = new Reflections("com.algorithms");

        for(Class<?> c: reflections.getSubTypesOf(GenerationStrategy.class)) {
            System.out.println(c.getCanonicalName());
            for(Method m: getMethods (c, withAnnotation(Filler.class))) {
                GenerationStrategy gs;
                HSSFSheet sheet = workbook.createSheet(c.getSimpleName());
                try {
                    gs = (GenerationStrategy) c.newInstance();

                    for(int i = 1; i < 8; i++) {
                        HSSFRow row = sheet.createRow(i);
                    }
                    for(int i = 5; i < 30; i+=5) {
                        arraySize = i;
                        minValue = random.nextInt(arraySize);
                        maxValue = random.nextInt(arraySize) + 4 * arraySize;
                        generatedArray = (Comparable[]) m.invoke(gs, arraySize, minValue, maxValue);
                        runAllSortingAlgorithmsForTheInput(generatedArray, sheet);
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void runAllSortingAlgorithmsForTheInput(Comparable[] input, HSSFSheet sheet) throws IOException {
        int rowCount = 0;
        int columnCount = 0;

        for(Class<?> c: reflections.getSubTypesOf(Sorting.class)) {
            for(Method m: getMethods(c, withAnnotation(Sorter.class))) {
                HSSFRow row = sheet.createRow(++rowCount);
                Sorting sorting;
                Comparable[] valuesToSort = input.clone();                          //TODO: is cloning appropriate?
                try {
                    sorting = (Sorting) c.getConstructor(Queue.class)
                            .newInstance(sortRepresentationQueue);
                    System.out.println("Before sort:" + Arrays.toString(valuesToSort));
                    System.out.println(c.getCanonicalName());
                    m.invoke(sorting, new Object[] {valuesToSort});
                    System.out.println("After sort:" + Arrays.toString(valuesToSort));
                    HSSFCell cell = row.createCell(++columnCount);
                    cell.setCellValue("" + valuesToSort.length + " " + c.getSimpleName());
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("sorts.xlsx")) {
            workbook.write(outputStream);
        }
    }
}
