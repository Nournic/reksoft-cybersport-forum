package com.reksoft.exporter.util;

import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

// Утилитный класс генерации отчета в csv-файл
@UtilityClass
@Slf4j
public class CsvGeneratorUtil {
    public static <T> File generateCsv(String filePath, String[] header,
                                       List<T> data, Function<T, String[]> mappingFunction)
    throws IOException{

        File file = new File(filePath);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))){
            writer.writeNext(header);

            for (T item : data){
                String[] line = mappingFunction.apply(item);
                writer.writeNext(line);
            }
        }
        return file;
    }
}
