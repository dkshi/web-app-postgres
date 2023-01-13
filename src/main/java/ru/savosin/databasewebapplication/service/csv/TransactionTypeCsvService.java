package ru.savosin.databasewebapplication.service.csv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.entity.TransactionType;
import ru.savosin.databasewebapplication.service.interfaces.CsvService;
import ru.savosin.databasewebapplication.service.interfaces.TransactionTypeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class TransactionTypeCsvService extends CsvConfig implements CsvService {

    private TransactionTypeService transactionTypeService;

    @Autowired
    public TransactionTypeCsvService(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @Override
    public void writeCsvFile(MultipartFile file) throws IOException {
        getRecords(file).forEach(record -> {
            TransactionType transactionType = new TransactionType();
            transactionType.setTrTypeId(Integer.parseInt(record.getString("tr_type")));
            transactionType.setTrDescription(record.getString("tr_description"));
            transactionTypeService.save(transactionType);
        });
        closeInputStream();
    }
    @Override
    public void exportCsvFile(HttpServletResponse response) throws Exception{

        String fileName = "tr-types.csv";

        List<TransactionType> transactionTypes = transactionTypeService.fetchAll();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"" );

        PrintWriter writer = response.getWriter();

        String[] header = new String[]{"tr_type", "tr_description"};
        writer.println(String.join(",", header));
        for(TransactionType transactionType : transactionTypes){
            writer.println(transactionType.toString());
        }
    }

}
