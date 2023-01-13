package ru.savosin.databasewebapplication.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CsvService {
    void writeCsvFile(MultipartFile file) throws IOException;
    void exportCsvFile(HttpServletResponse response) throws Exception;
}
