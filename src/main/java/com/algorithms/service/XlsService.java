package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import com.algorithms.util.SheetUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

/**
 * Manages process of writing result of the sorting
 * analysis into an xls file
 *
 * @author Zemlianiy
 * @version 1.0
 * @since
 */
@Service
public class XlsService implements Writing {

    private static final Logger log = LoggerFactory.getLogger(XlsService.class);

    public static final int BEGINNING_OF_THE_TABLE = 1;
    private Queue<SortRepresentation> sortRepresentationQueue;
    private Random random = new Random(47);

    @Autowired
    public XlsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    /**
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IOException
     */
    @Override
    public void generateStatistics() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException {

        Comparable[] generatedArray;

        Reflections reflections = new Reflections("com.algorithms");

        int rowIndex = 1;
        int columnIndex = 1;

        HSSFWorkbook workbook = new HSSFWorkbook();
        for (Class<?> gen : reflections.getSubTypesOf(GenerationStrategy.class)) {
            SheetUtil sheetUtil = new SheetUtil(workbook, gen.getSimpleName());
            sheetUtil.createMultipleRows(7);

            GenerationStrategy gs = (GenerationStrategy) gen.newInstance();
            for (Method generateMethod : getMethods(gen, withAnnotation(Filler.class))) {
                log.info("Starting filler: {}", gen.getSimpleName());
                for (int i = 5; i < 25; i += 5) {
                    log.info("For number of elements: {}", i);
                    Range range = getSampleDataRange(i);
                    generatedArray = (Comparable[]) generateMethod.invoke(gs,
                            range.getArraySize(), range.getMinValue(), range.getMaxValue());

                    for (Class<?> sort : reflections.getSubTypesOf(Sorting.class)) {
                        Comparable[] valuesToSort = generatedArray.clone();
                        Sorting sorting = (Sorting) sort.getConstructor(Queue.class)
                                .newInstance(sortRepresentationQueue);
                        for(Method sortMethod: getMethods(sort, withAnnotation(Sorter.class))) {
                            log.info("Starting sort: {}", sort.getSimpleName());
                            sortMethod.invoke(sorting, new Object[]{valuesToSort});
                        }
                        SortRepresentation last = sortRepresentationQueue.getLast();
                        Long elapsedTime = last.getElapsedTime();

                        sheetUtil.createHeaderForRow(rowIndex, sort.getSimpleName());

                        sheetUtil.createHeaderForColumn(columnIndex, i);

                        sheetUtil.writeValueToCell(rowIndex++, columnIndex, elapsedTime);
                    }
                    columnIndex++;
                    rowIndex = BEGINNING_OF_THE_TABLE;
                }
            }
            columnIndex = 1;
        }

        try (FileOutputStream outputStream = new FileOutputStream("sorts.xlsx")) {
            workbook.write(outputStream);
        }
    }

    private Range getSampleDataRange(int rangeSize) {
        int minValue = random.nextInt(rangeSize);
        int maxValue = random.nextInt(rangeSize) + 4 * rangeSize;

        return new Range(rangeSize, minValue, maxValue);
    }
}
