package ru.savosin.databasewebapplication.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.service.csv.TransactionTypeCsvService;
import ru.savosin.databasewebapplication.entity.TransactionType;
import ru.savosin.databasewebapplication.service.interfaces.TransactionTypeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("tables")
public class TransactionTypeController {

    private TransactionTypeService transactionTypeService;

    private TransactionTypeCsvService transactionTypeCsvService;

    @Autowired
    public TransactionTypeController(TransactionTypeService transactionTypeService,
                                     TransactionTypeCsvService transactionTypeCsvService) {
        this.transactionTypeService = transactionTypeService;
        this.transactionTypeCsvService = transactionTypeCsvService;
    }

    @GetMapping("tr-types")
    public String showTrType(Model model){
        model.addAttribute("transactionTypes", transactionTypeService.fetchAll());
        return "tables/tr-types";
    }

    @GetMapping("/exportTransactionTypes")
    public void exportGenderTrain(HttpServletResponse response) throws Exception {
        transactionTypeCsvService.exportCsvFile(response);
    }

    @PostMapping("tr-types")
    public String addTrType(@RequestParam(value = "trTypeId", required = false) Integer trTypeId,
                            @RequestParam(value = "trDescription", required = false) String trDescription,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            Model model) throws IOException {
        if (file != null)
            transactionTypeCsvService.writeCsvFile(file);

        if (trTypeId != null)
            transactionTypeService.save(new TransactionType(trTypeId, trDescription));

        model.addAttribute("transactionTypes", transactionTypeService.fetchAll());
        return "tables/tr-types";
    }
}
