package com.reksoft.exporter.service.report;

import java.io.File;
import java.io.IOException;

public interface ReportService {
    File generateReport(String filePath) throws IOException;
}
