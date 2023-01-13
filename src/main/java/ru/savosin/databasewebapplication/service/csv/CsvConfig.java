package ru.savosin.databasewebapplication.service.csv;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

public class CsvConfig {
    private static final CsvParserSettings settings = new CsvParserSettings();
    private static final CsvParser parser = new CsvParser(settings);

    private static InputStream inputStream;

    static {
        settings.setDelimiterDetectionEnabled(true);
        settings.setHeaderExtractionEnabled(true);
    }

    public List<Record> getRecords(MultipartFile file) throws IOException {
        inputStream = file.getInputStream();
        return parser.parseAllRecords(inputStream);
    }

    public void closeInputStream() throws IOException {
        inputStream.close();
    }

}
