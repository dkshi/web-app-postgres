package ru.savosin.databasewebapplication.service.csv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.entity.Transaction;
import ru.savosin.databasewebapplication.service.interfaces.CsvService;
import ru.savosin.databasewebapplication.service.interfaces.TransactionService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionCsvService extends CsvConfig implements CsvService {

    private TransactionService transactionService;


    @Autowired
    public TransactionCsvService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void writeCsvFile(MultipartFile file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        getRecords(file).forEach(record -> {
            Transaction transaction = new Transaction();
            transaction.setCustomerId(Integer.parseInt(record.getString("customer_id")));
            transaction.setTrDateTime(LocalDateTime.parse(record.getString("tr_datetime"), formatter));
            transaction.setMccCodeId(Integer.parseInt(record.getString("mcc_code")));
            transaction.setTrTypeId(Integer.parseInt(record.getString("tr_type")));
            transaction.setAmount(Integer.parseInt(record.getString("amount")));
            transaction.setTerminalId(Integer.parseInt(record.getString("term_id")));
            transactionService.save(transaction);
        });
        closeInputStream();
    }
    @Override
    public void exportCsvFile(HttpServletResponse response) throws Exception{
        String fileName = "transactions.csv";

        List<Transaction> transactions = transactionService.fetchAll();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"" );

        PrintWriter writer = response.getWriter();

        String[] header = new String[]{"customer_id", "mcc_code", "tr_datetime","tr_type", "amount", "term_id"};
        writer.println(String.join(",", header));
        for(Transaction transaction : transactions){
            writer.println(transaction.toString());
        }
    }

    public void exportCsvFile(List<Transaction> findings,HttpServletResponse response) throws Exception{
        String fileName = "findings.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"" );

        PrintWriter writer = response.getWriter();

        String[] header = new String[]{"customer_id", "mcc_code", "tr_datetime","tr_type", "amount", "term_id"};
        writer.println(String.join(",", header));
        for(Transaction finding : findings){
            writer.println(finding.toString());
        }
    }



}
