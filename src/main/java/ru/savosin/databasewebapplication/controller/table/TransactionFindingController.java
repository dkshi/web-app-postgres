package ru.savosin.databasewebapplication.controller.table;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.savosin.databasewebapplication.entity.Transaction;
import ru.savosin.databasewebapplication.service.csv.TransactionCsvService;
import ru.savosin.databasewebapplication.service.interfaces.TransactionService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/tables")
public class TransactionFindingController {
    private List<Transaction> findings;

    private TransactionService transactionService;
    private TransactionCsvService transactionCsvService;

    @Autowired
    public TransactionFindingController(TransactionService transactionService,
                                        TransactionCsvService transactionCsvService) {
        this.transactionService = transactionService;
        this.transactionCsvService = transactionCsvService;
    }

    @GetMapping("finding")
    public String showFindings(){
        findings = null;
        return "tables/finding";
    }

    @PostMapping("finding")
    public String getFindings(@RequestParam(value = "trDescription", required = false) String trDescription, Model model){
        findings = transactionService.findByTrDescription(trDescription);
        model.addAttribute("findings", transactionService.findByTrDescription(trDescription));
        model.addAttribute("trDescription", trDescription);
        return "tables/finding";
    }

    @GetMapping("/exportFindings")
    public void exportFindings(HttpServletResponse response) throws Exception {
        transactionCsvService.exportCsvFile(findings, response);
    }
}
