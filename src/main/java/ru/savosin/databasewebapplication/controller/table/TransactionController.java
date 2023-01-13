package ru.savosin.databasewebapplication.controller.table;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.service.csv.TransactionCsvService;
import ru.savosin.databasewebapplication.entity.Transaction;
import ru.savosin.databasewebapplication.service.interfaces.TransactionService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("tables")
public class TransactionController {

    private TransactionService transactionService;
    private TransactionCsvService transactionCsvService;

    @Autowired
    public TransactionController(TransactionService transactionService,
                                 TransactionCsvService transactionCsvService) {
        this.transactionService = transactionService;
        this.transactionCsvService = transactionCsvService;
    }

    @GetMapping("transactions")
    public String showTransaction(Model model){
        model.addAttribute("transactions", transactionService.fetchAll());
        return "tables/transactions";
    }

    @GetMapping("/exportTransactions")
    public void exportGenderTrain(HttpServletResponse response) throws Exception {
        transactionCsvService.exportCsvFile(response);
    }

    @PostMapping("transactions")
    public String addTransaction(@RequestParam(value = "customerId", required = false) Integer customerId,
                                 @RequestParam(value = "mccCodeId", required = false) Integer mccCodeId,
                                 @RequestParam(value = "trDateTime", required = false) String trDateTime,
                                 @RequestParam(value = "trTypeId", required = false) Integer trTypeId,
                                 @RequestParam(value = "amount", required = false) Integer amount,
                                 @RequestParam(value = "terminalId", required = false) Integer terminalId,
                                 @RequestParam(value = "file", required = false) MultipartFile file,
                                 Model model) throws IOException {
        if (file != null)
            transactionCsvService.writeCsvFile(file);


        if (customerId != null || mccCodeId != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            transactionService.save(new Transaction(customerId,
                    mccCodeId, LocalDateTime.parse(trDateTime, formatter), trTypeId, amount, terminalId));
        }
        model.addAttribute("transactions", transactionService.fetchAll());
        return "tables/transactions";
    }
}
