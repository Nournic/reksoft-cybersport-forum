package com.reksoft.exporter.util;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvGeneratorUtilTest {

    @Test
    void shouldGenerateCsvFile() throws Exception {
        String filePath = System.getProperty("java.io.tmpdir") + File.separator + "test.csv";
        String[] header = {"ID", "Name"};
        List<TestData> data = List.of(new TestData(1, "John"), new TestData(2, "Jane"));
        java.util.function.Function<TestData, String[]> mapper = d -> new String[]{String.valueOf(d.id), d.name};

        File file = CsvGeneratorUtil.generateCsv(filePath, header, data, mapper);

        assertThat(file).exists();

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> lines = reader.readAll();
            assertThat(lines).hasSize(3);
            assertThat(lines.get(0)).containsExactly("ID", "Name");
            assertThat(lines.get(1)).containsExactly("1", "John");
            assertThat(lines.get(2)).containsExactly("2", "Jane");
        } finally {
            file.delete();
        }
    }

    private static class TestData {
        int id;
        String name;
        TestData(int id, String name) { this.id = id; this.name = name; }
    }
}